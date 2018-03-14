package api.provider;


import api.dto.SecurityDepositDTO;

/**
 * Created by wujinliang on 2018/3/8.
 */
public interface SecurityDepositProvider {
    /**
     * 通过id查找渠道
     *
     * @param id
     * @return
     */
    SecurityDepositDTO findCreditById(Integer id);

    /**
     * 更新渠道保证金
     *
     * @param securityDepositDTO
     * @return
     */
    void updateCredit(SecurityDepositDTO securityDepositDTO);

    /**
     * 通过Redis查询缓存数据
     *
     * @param id
     * @return
     */
    SecurityDepositDTO redisCachefindCreditById(Integer id);
}
