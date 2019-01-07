package com.demo.api.user.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Lye
 */
@TableName("dt_property_owners")
@Data
public class PropertyOwner {
    /**
     * 资料类型：用户信息
     */
    public static final Integer OWNER_TYPE_USER_INFO = 1;
    /**
     * 资料类型：产权人信息
     */
    public static final Integer OWNER_TYPE_PROPERTY_OWNER = 2;
    @TableId
    private Integer id;
    private Integer belongAccount;
    private String name;
    private String mobile;
    private String certificate;
    private String idCard;
    private String address;
    private Boolean isDefault;
    private Date createTime;
    private Date modifyTime;
    private BigDecimal propertyProprotion;
    private Integer type;
}
