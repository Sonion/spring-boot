package api.provider;

import api.config.StringKeyRedisTemplate;
import api.dto.SecurityDepositDTO;
import api.service.PlatformService;
import api.util.SecurityDepositDomainConverter;
import com.alibaba.fastjson.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Component
public class SecurityDepositProviderImpl implements SecurityDepositProvider {

    private static Logger logger = LoggerFactory.getLogger(SecurityDepositProviderImpl.class);
    @Resource
    PlatformService platformService;

    @Autowired
    private StringKeyRedisTemplate cacheStringKeyRedisTemplate;

    @Override
    public SecurityDepositDTO findCreditById(Integer id) {
        return SecurityDepositDomainConverter.convert(platformService.findCreditById(id));
    }

    @Override
    public void updateCredit(SecurityDepositDTO securityDepositDTO){
        platformService.updateCredit(securityDepositDTO);
    }

    @Override
    public SecurityDepositDTO redisCachefindCreditById(Integer id) {
        String key = "platfromId_" + id;
        String s = JSON.toJSONString(cacheStringKeyRedisTemplate.opsForValue().get(key));
        SecurityDepositDTO securityDepositDTO = JSON.parseObject(s, SecurityDepositDTO.class);
        // 缓存存在
        boolean hasKey = cacheStringKeyRedisTemplate.hasKey(key);
        if (hasKey){
            logger.info("从Redis缓存中获取了保证金_" + securityDepositDTO.getPlatformId());
            return securityDepositDTO;
        }
        //从 DB 中获取保证金数据
        securityDepositDTO = findCreditById(id);
        //数据初始化
        if (securityDepositDTO == null){
            logger.info("数据库无数据，配置默认信息_"+ id);
            securityDepositDTO = new SecurityDepositDTO().platformId(id).credit(0).tagNum(0).platformTag(0);

        }
        //插入Redis缓存
        cacheStringKeyRedisTemplate.opsForValue().set(key,securityDepositDTO,15,TimeUnit.MINUTES);
        logger.info("保证金插入redisCache_" + securityDepositDTO.toString());
        return securityDepositDTO;
    }


}
