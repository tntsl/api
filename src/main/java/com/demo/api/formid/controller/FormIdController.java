package com.demo.api.formid.controller;

import com.demo.api.common.domain.Result;
import com.demo.api.common.util.ValidResultUtils;
import com.demo.api.formid.domain.Form;
import com.demo.api.formid.service.FormIdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Create by lixz
 * @Description:
 */
@Api(tags = "收集formID")
@RestController
@RequestMapping("/formId")
public class FormIdController {
    @Autowired
    FormIdService formIdService;

    @ApiOperation("收集formID")
    @RequiresPermissions("collectFormId")
    @PostMapping(value = "/collectFormId", params = {"v=1.0"})
    public Result collectFormId(@Valid @RequestBody Form form, BindingResult result) {
        if (result.hasErrors()) {
            return new Result().failure().setMessage(ValidResultUtils.resultsToString(result));
        }
        formIdService.save(form);
        return new Result().success();
    }
}
