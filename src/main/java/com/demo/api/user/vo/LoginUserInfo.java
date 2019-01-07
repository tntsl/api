package com.demo.api.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @author Lye
 */
@ApiModel
@Data
public class LoginUserInfo {
    /**
     * openId
     */
    @ApiModelProperty
    private String wechatId;
    /**
     * 用户唯一Id
     */
    @ApiModelProperty("用户Id")
    private Integer id;
    /**
     * 用户昵称
     */
    @ApiModelProperty("昵称")
    private String nickName;
    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String avatar;
    /**
     * 是否激活
     */
    @ApiModelProperty("是否激活")
    private Boolean isActived;
    /**
     * 最新在用token
     */
    @ApiModelProperty("通行证")
    private String token;
    /**
     * 最后登录时间
     */
    @ApiModelProperty("最近一次登录时间")
    private Date lastLoginTime;

    /**
     * 用户权限
     */
    private Set<String> permissions;
}
