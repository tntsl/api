package com.demo.api.wechat.service;

import com.demo.api.common.domain.SystemInfo;
import com.demo.api.common.exception.WechatLoginException;
import com.demo.api.common.util.GsonUtils;
import com.demo.api.common.service.HttpClientPool;
import com.demo.api.wechat.domain.RepForWechatSession;
import com.google.common.collect.Lists;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author Lye
 */
@Service
public class WechatService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatService.class);
    @Autowired
    private SystemInfo systemInfo;
    @Autowired
    private HttpClientPool httpClientPool;

    public RepForWechatSession getWechatInfoByCode(String code) throws WechatLoginException {
        try {
            HttpPost post = new HttpPost(systemInfo.getWechat().getSessionKeyUrl());
            List<NameValuePair> params = Lists.newArrayList();
            params.add(new BasicNameValuePair("appid", systemInfo.getWechat().getAppid()));
            params.add(new BasicNameValuePair("secret", systemInfo.getWechat().getSecret()));
            params.add(new BasicNameValuePair("grant_type", "authorization_code"));
            params.add(new BasicNameValuePair("js_code", code));
            post.setEntity(new UrlEncodedFormEntity(params));
            CloseableHttpResponse httpResponse = httpClientPool.getHttpClient().execute(post);
            String content = EntityUtils.toString(httpResponse.getEntity());
            LOGGER.info("code：{} 返回的结果：{}", code, content);
            return GsonUtils.fromJson(content, RepForWechatSession.class);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        throw new WechatLoginException("获取微信授权失败");
    }
}
