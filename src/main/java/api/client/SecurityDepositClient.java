package api.client;

import api.dto.SecurityDepositDTO;
import api.provider.SecurityDepositProvider;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


/**
 * Created by wujinliang on 2018/3/8.
 */
public final class SecurityDepositClient {

    private static Logger logger = LoggerFactory.getLogger(SecurityDepositClient.class);

    private static SecurityDepositProvider securityDepositProvider;

    /**
     * 通过渠道ID获取渠道保证金信息
     * @param id
     * @return
     */
    public static SecurityDepositDTO loadCreditById(Integer id) {
        checkInitialized();
        try{
            Optional<SecurityDepositDTO> securityDeposit = creditCache.get(id);
            if (securityDeposit.isPresent()) {
                logger.info("Guava缓存中获取保证金_" + id);
                return securityDeposit.get();
            } else {
                SecurityDepositDTO securityDepositDTO = securityDepositProvider.redisCachefindCreditById(id);
                return securityDepositDTO;
            }
        }catch (ExecutionException e) {
            logger.error("保证金获取异常_" + id + " exception.", e);
            return null;
        }
    }

    /**
     * 缓存渠道id获取保证金
     */
    private final static LoadingCache<Integer, Optional<SecurityDepositDTO>> creditCache = CacheBuilder.newBuilder()
            .expireAfterAccess(10, TimeUnit.SECONDS)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build(new CacheLoader<Integer, Optional<SecurityDepositDTO>>() {
                @Override
                public Optional<SecurityDepositDTO> load(Integer id) throws Exception {
                    return Optional.ofNullable(securityDepositProvider.findCreditById(id));
                }
            });

    /**
     * 更新渠道保证金信息
     * @param securityDepositDTO
     * @return
     */
    public static void updateCredit(SecurityDepositDTO securityDepositDTO){
        checkInitialized();
        securityDepositProvider.updateCredit(securityDepositDTO);
    }

    /**
     * 检查客户端有没有被成功初始化
     */
    private static void checkInitialized() {
        if (securityDepositProvider == null) {
            throw new IllegalStateException("SecurityDepositClient并未初始化，请使用SecurityDepositClient#setSecurityDepositProvider注入依赖");
        }
    }

    @Autowired
    public void setSecurityDepositProvider(SecurityDepositProvider securityDepositProvider) {
        SecurityDepositClient.securityDepositProvider = securityDepositProvider;
    }
}
