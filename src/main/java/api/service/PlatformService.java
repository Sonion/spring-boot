package api.service;

import api.dto.SecurityDepositDTO;
import api.entity.SecurityDepositPO;
import api.mapper.SecurityDepositPOMapper;
import api.util.SecurityDepositDomainConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class PlatformService {

    private static Logger logger = LoggerFactory.getLogger(PlatformService.class);

    @Resource
    SecurityDepositPOMapper securityDepositPOMapper;

    /**
     * 通过id查找保证金信息
     *
     * @param id
     * @return
     */
    @Cacheable
    public SecurityDepositPO findCreditById(Integer id) {
        return securityDepositPOMapper.selectCreditByPlatformId(id);
    }

    /**
     * 更新保证金操作
     *
     * @param securityDepositDTO
     */
    public void updateCredit(SecurityDepositDTO securityDepositDTO) {

         SecurityDepositPO securityDepositPO = SecurityDepositDomainConverter.convert(securityDepositDTO);
        //如果数据库不存在就插入
        if (securityDepositPOMapper.selectCreditByPlatformId(securityDepositPO.getPlatformId()) == null){
            securityDepositPOMapper.insertSelective(securityDepositPO);
        }
        else {
            try{
                securityDepositPOMapper.updateCreditByPlatformId(securityDepositPO);
            }catch (Exception e){
                logger.warn(securityDepositPO.getPlatformId()+"渠道信息数据库存在，更新异常");
            }

        }

    }
}
