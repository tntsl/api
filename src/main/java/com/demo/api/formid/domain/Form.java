package com.demo.api.formid.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Create by lixz
 * @Description:
 */
@ApiModel
@Data
public class Form {
    @NotNull(message = "不能为空")
    @ApiModelProperty(name="formId",value = "小程序的formid")
    private String [] formId;
}
