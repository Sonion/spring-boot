package api.mapper;

import api.entity.SecurityDepositPO;
import api.entity.SecurityDepositPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecurityDepositPOMapper {

    SecurityDepositPO selectCreditByPlatformId(Integer id);

    int updateCreditByPlatformId(SecurityDepositPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_security_deposit
     *
     * @mbg.generated Tue Mar 13 20:34:58 CST 2018
     */
    long countByExample(SecurityDepositPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_security_deposit
     *
     * @mbg.generated Tue Mar 13 20:34:58 CST 2018
     */
    int deleteByExample(SecurityDepositPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_security_deposit
     *
     * @mbg.generated Tue Mar 13 20:34:58 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_security_deposit
     *
     * @mbg.generated Tue Mar 13 20:34:58 CST 2018
     */
    int insert(SecurityDepositPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_security_deposit
     *
     * @mbg.generated Tue Mar 13 20:34:58 CST 2018
     */
    int insertSelective(SecurityDepositPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_security_deposit
     *
     * @mbg.generated Tue Mar 13 20:34:58 CST 2018
     */
    List<SecurityDepositPO> selectByExample(SecurityDepositPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_security_deposit
     *
     * @mbg.generated Tue Mar 13 20:34:58 CST 2018
     */
    SecurityDepositPO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_security_deposit
     *
     * @mbg.generated Tue Mar 13 20:34:58 CST 2018
     */
    int updateByExampleSelective(@Param("record") SecurityDepositPO record, @Param("example") SecurityDepositPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_security_deposit
     *
     * @mbg.generated Tue Mar 13 20:34:58 CST 2018
     */
    int updateByExample(@Param("record") SecurityDepositPO record, @Param("example") SecurityDepositPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_security_deposit
     *
     * @mbg.generated Tue Mar 13 20:34:58 CST 2018
     */
    int updateByPrimaryKeySelective(SecurityDepositPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table platform_security_deposit
     *
     * @mbg.generated Tue Mar 13 20:34:58 CST 2018
     */
    int updateByPrimaryKey(SecurityDepositPO record);
}