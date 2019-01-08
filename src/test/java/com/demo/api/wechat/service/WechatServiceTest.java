package com.demo.api.wechat.service;

import com.demo.api.ApplicationTests;
import com.demo.api.common.exception.WechatLoginException;
import com.demo.api.common.util.GsonUtils;
import com.demo.api.wechat.domain.RepForWechatSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WechatServiceTest extends ApplicationTests {
    @Autowired
    private WechatService wechatService;

    @Test
    public void getWechatInfoByCode() {
        try {
            RepForWechatSession repForWechatSession = wechatService.getWechatInfoByCode("061vDN3O09OWk32H9D2O0EsL3O0vDN3Q");
            System.out.println(GsonUtils.toJsonWithNulls(repForWechatSession));
        } catch (WechatLoginException e) {
            e.printStackTrace();
        }
    }
}
