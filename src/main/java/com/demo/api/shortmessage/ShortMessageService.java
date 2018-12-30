package com.demo.api.shortmessage;

import java.util.Map;
import java.util.Set;

/**
 * @author Lye
 */
public interface ShortMessageService {

    /**
     * 批量发送短信
     *  @param mobiles
     * @param content
     */
    public Map<String, String> sendMessage(Set<String> mobiles, String content);

    /**
     * 单个手机发送短信
     *  @param mobile
     * @param content
     */
    public String sendMessage(String mobile, String content);
}
