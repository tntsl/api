package com.demo.api.user.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Lye
 */
@ApiModel
@Data
public class ReqForRegist {

    @ApiModelProperty(required = true, value = "注册手机号")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式错误")
    private String mobile;
    @ApiModelProperty(required = true, value = "手机验证码")
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;
    @ApiModelProperty(required = true, value = "证件类型")
    @NotBlank(message = "证件类型不能为空")
    private String certificateType;
    @ApiModelProperty(required = true, value = "证件号")
    @NotBlank(message = "证件号不能为空")
    @Pattern(regexp = "\\d{17}[\\d|x]|\\d{15}|[a-zA-Z0-9]{7,21}|[a-zA-Z0-9]{5,21}|[a-zA-Z0-9]{3,21}", message = "证件格式错误")
    private String certificateId;
    @ApiModelProperty(required = true, value = "登记姓名")
    @NotBlank(message = "登记姓名不能为空")
    private String name;
}
