package com.demo.api.formid.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.demo.api.common.util.RedisOperator;
import com.demo.api.formid.domain.Form;
import com.demo.api.jfinal.model.DtFormId;
import com.demo.api.user.domain.LoginUserInfo;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author Create by lixz
 * @Description:
 */
@Service
public class FormIdService {
    @Autowired
    private RedisOperator redisOperator;

    public void save(Form form) {
        String openId = (String) SecurityUtils.getSubject().getPrincipal();
        LoginUserInfo loginUserInfo = redisOperator.getLoginUserInfoByOpenId(openId);
        String[] formId = form.getFormId();
        for (String formid : formId) {
            DtFormId dtFormId = new DtFormId();
            dtFormId.setUserId(loginUserInfo.getId());
            dtFormId.setFormId(formid);
            Date date = DateUtils.addDays(new Date(), 7);
            dtFormId.setFailureTime(date);
            Record record = new Record();
            record.setColumns(dtFormId);
            Db.use("ds1").save("dt_form_id", record);
        }


    }
}
