package api.util;

import api.dto.SecurityDepositDTO;
import api.entity.SecurityDepositPO;
import org.springframework.cglib.beans.BeanCopier;

/**
 * Created by wujinliang on 2018/3/12.
 */
public class SecurityDepositDomainConverter {
    private static final BeanCopier PO_2_DTO = BeanCopier.create(SecurityDepositPO.class, SecurityDepositDTO.class, false);
    private static final BeanCopier DTO_2_PO = BeanCopier.create(SecurityDepositDTO.class, SecurityDepositPO.class, false);

    private SecurityDepositDomainConverter(){}

    /**
     * 从SecurityDepositPO转化为SecurityDepositDTO
     * @param securityDepositPO
     * @return
     */
    public static final SecurityDepositDTO convert(SecurityDepositPO securityDepositPO) {
        if (securityDepositPO == null) {
            return null;
        }

        SecurityDepositDTO dto = new SecurityDepositDTO();
        PO_2_DTO.copy(securityDepositPO, dto, null);
        return dto;
    }

    /**
     * 从SecurityDepositDTO转化为SecurityDepositPO
     * @param securityDepositDTO
     * @return
     */
    public static final SecurityDepositPO convert(SecurityDepositDTO securityDepositDTO) {
        if (securityDepositDTO == null) {
            return null;
        }

        SecurityDepositPO securityDepositPO = new SecurityDepositPO();
        DTO_2_PO.copy(securityDepositDTO, securityDepositPO, null);
        return securityDepositPO;
    }
}
