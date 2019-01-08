package com.demo.api.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Lye
 */
@ApiModel("微信code登录")
@Data
public class ReqForWechatLogin {
    @ApiModelProperty(required = true, value = "微信Code")
    @NotBlank(message = "微信Code不能为空")
    private String code;
    @ApiModelProperty("微信昵称")
    private String nickName;
    @ApiModelProperty("微信头像")
    private String avatar;
}
