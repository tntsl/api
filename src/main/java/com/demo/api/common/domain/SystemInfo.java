package com.demo.api.common.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Lye
 */
@Data
@ConfigurationProperties(prefix = "api")
public class SystemInfo {
    /**
     * 存储微信配置
     */
    private Wechat wechat;

    /**
     * 存储系统短信配置
     */
    private ShortMessage shortMessage;

    /**
     * 全局token获取名称
     */
    private String tokenHeader;

    /**
     * token过期时长
     */
    private Integer tokenExpire;
    /**
     * token加密密钥
     */
    private String tokenSecurityKey;
    /**
     * token颁发者
     */
    private String tokenIssuer;
    /**
     * token校验单位
     */
    private String tokenAudience;
}
