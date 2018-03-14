package api.mapper;



import api.entity.SecurityDepositPO;

import javax.annotation.Resource;

/**
 * Created by wujinliang on 2018/3/12.
 */
public class SecurityDepositManager {

    @Resource
    SecurityDepositPOMapper securityDepositPOMapper;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     */
    public SecurityDepositPO findCreditById(Integer id) {
        return securityDepositPOMapper.selectByPrimaryKey(id);
    }

}
