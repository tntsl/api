package com.demo.api.common.domain;

import lombok.Data;

/**
 * @author Lye
 */
@Data
public class Wechat {
    private String sessionKeyUrl;
    private String appid;
    private String secret;
    private String accessTokenPath;
    private String templateTitleListPath;
    private String templateKeywordsListPath;
    private String templateAddPath;
    private String templateListPath;
    private String templateDelPath;
    private String messageSendByTemplatePath;
}
