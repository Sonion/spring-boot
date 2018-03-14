package api.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wujinliang on 2018/3/8.
 */
@Data
@ToString(exclude = {"id","createTime","updateTime"})
public class SecurityDepositDTO implements Serializable {
    private static final long serialVersionUID = 1l;

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 关联的渠道Id
     */
    @NotNull
    private Integer platformId;
    /**
     * 标签获得天数
     */
    @NotNull
    private Integer tagNum;
    /**
     * 渠道业务标签
     */
    @NotNull
    private Integer platformTag;
    /**
     * 保证金额度
     */
    @NotNull
    private Integer credit;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public SecurityDepositDTO platformId(Integer platformId){
        this.platformId = platformId;
        return this;
    }
    public SecurityDepositDTO platformTag(Integer platformTag){
        this.platformTag = platformTag;
        return this;
    }

    public SecurityDepositDTO tagNum(Integer tagNum){
        this.tagNum = tagNum;
        return this;
    }

    public SecurityDepositDTO credit(Integer credit){
        this.credit = credit;
        return  this;
    }
}
