package com.demo.api.wechat.service;

import com.google.gson.Gson;
import com.demo.api.common.GlobalConstParam;
import com.demo.api.common.domain.SystemInfo;
import com.demo.api.common.domain.Wechat;
import com.demo.api.wechat.domain.RepForAccessToken;
import com.demo.api.wechat.domain.RepForSendMessageByTemplate;
import com.demo.api.wechat.domain.ReqForSendMessageByTemplate;
import com.demo.api.common.util.HttpClientPool;
import com.demo.api.common.util.RedisOperator;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Lye
 */
@Service
public class TemplateMsgService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateMsgService.class);
    @Autowired
    private SystemInfo systemInfo;
    @Autowired
    private HttpClientPool httpClientPool;
    @Autowired
    private RedisOperator redisOperator;

    /**
     * 获取Access-token
     *
     * @return
     */
    public String getAccessToken() {
        Wechat wechat = systemInfo.getWechat();
        String accessToken = redisOperator.get(GlobalConstParam.TEMPLATE_MESSAGE_ACCESS_TOKEN);
        if (StringUtils.isNotBlank(accessToken)) {
            return accessToken;
        } else {
            HttpGet get = new HttpGet(wechat.getAccessTokenPath().concat("&appid=").concat(wechat.getAppid()).concat("&secret=").concat(wechat.getSecret()));
            try {
                CloseableHttpResponse response = httpClientPool.getHttpClient().execute(get);
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);
                RepForAccessToken repForAccessToken = new Gson().fromJson(content, RepForAccessToken.class);
                accessToken = repForAccessToken.getAccessToken();
                redisOperator.set(GlobalConstParam.TEMPLATE_MESSAGE_ACCESS_TOKEN, accessToken);
                redisOperator.expire(GlobalConstParam.TEMPLATE_MESSAGE_ACCESS_TOKEN, 3540L*1000);
                return accessToken;
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * 发送模板消息
     */
    public RepForSendMessageByTemplate sendMessageByTemplate(ReqForSendMessageByTemplate reqForSendMessageByTemplate) {
        HttpPost post = new HttpPost(systemInfo.getWechat().getMessageSendByTemplatePath().concat("?access_token=").concat(getAccessToken()));
        try {
            HttpEntity httpEntity = new StringEntity(new Gson().toJson(reqForSendMessageByTemplate),"UTF-8");
            post.setEntity(httpEntity);
            CloseableHttpResponse response = httpClientPool.getHttpClient().execute(post);
            String content = EntityUtils.toString(response.getEntity());
            return new Gson().fromJson(content, RepForSendMessageByTemplate.class);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}
