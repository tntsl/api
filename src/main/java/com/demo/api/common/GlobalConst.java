package com.demo.api.common;

/**
 * @author Lye
 */
public class GlobalConst {

    private GlobalConst() {

    }

    /**
     * redis中缓存的拥有openid的用户信息
     */
    public static final String WECHAT_USER = "api_wechat_user";
    /**
     * 缓存到redis中的微信access-token
     */
    public static final String TEMPLATE_MESSAGE_ACCESS_TOKEN = "cms_template_message_access_token";
    /**
     * 手机注册验证码
     */
    public static final String MOBILE_REGIST_VERIFYCODE = "api_mobile_regist_verifycode";
    public static final String MOBILE_REGIST_VERIFYCODE_LIMIT = "api_mobile_regist_verifycode_limit";
    /**
     * redis中缓存的公司字典
     */
    public static final String SYSTEM_DIC_COMPANYS = "api_system_dic_companys";
    /**
     * redis中缓存的项目字典
     */
    public static final String SYSTEM_DIC_PROJECTS = "api_system_dic_projects";

    public static final String WX_STEP_RECORD = "api_wechat_step_record";
}
