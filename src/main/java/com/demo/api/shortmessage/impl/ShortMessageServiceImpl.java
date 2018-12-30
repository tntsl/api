package com.demo.api.shortmessage.impl;

import com.demo.api.common.domain.ShortMessage;
import com.demo.api.common.domain.SystemInfo;
import com.demo.api.common.util.MD5Utils;
import com.demo.api.shortmessage.ShortMessageService;
import com.demo.api.shortmessage.cxf.WebService;
import com.demo.api.shortmessage.cxf.WebServiceSoap;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * @author Lye
 */
@Service
public class ShortMessageServiceImpl implements ShortMessageService {

    @Autowired
    private SystemInfo systemInfo;
    @Autowired
    private WebService webService;

    @Override
    public Map<String, String> sendMessage(Set<String> mobiles, String content) {
        Map<String, String> result = Maps.newHashMap();
        for (String mobile : mobiles) {
            String mdgxsend = sendMessage(mobile, content);
            result.put(mobile, mdgxsend);
        }
        return result;
    }

    @Override
    public String sendMessage(String mobile, String content) {
        WebServiceSoap soap = webService.getWebServiceSoap12();
        ShortMessage shortMessage = systemInfo.getShortMessage();
        String sn = shortMessage.getSn();
        String md5 = MD5Utils.getMD5(sn + shortMessage.getPwd());
        String hookMobile = systemInfo.getShortMessage().getHookMobile();
        if (StringUtils.isNotBlank(hookMobile)) {
            mobile = hookMobile;
        }
        return soap.mdgxsend(sn, md5, mobile, content, "", "", "", "");
    }
}
