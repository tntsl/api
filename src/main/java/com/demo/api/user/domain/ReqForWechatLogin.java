package com.demo.api.user.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel("微信code登录")
@Data
public class ReqForWechatLogin {
    @ApiModelProperty(required = true, value = "微信Code")
    @NotBlank(message = "微信Code不能为空")
    private String code;
}
