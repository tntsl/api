package com.demo.api.common.util;

import com.demo.api.common.domain.SystemInfo;
import com.demo.api.user.domain.LoginUserInfo;
import com.demo.api.common.GlobalConstParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoujun
 * @version V1.0
 * @Title: RedisOperator.java
 * @Package com.itzixi.web.component
 * @Description: 使用redisTemplate的操作實現類 Copyright: Copyright (c) 2016
 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
 * @date 2018年3月29日 下午2:25:03
 */
@Component
public class RedisOperator {

    @Autowired
    private SystemInfo systemInfo;
    @Autowired
    private StringRedisTemplate redisTemplate;

    // Key（鍵），簡單的key-value操作

    /**
     * 實現命令：TTL key，以秒為單位，返回給定 key的剩餘生存時間(TTL, time to live)。
     *
     * @param key
     * @return
     */
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 實現命令：expire 設置過期時間，單位秒
     *
     * @param key
     * @return
     */
    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 實現命令：INCR key，增加key一次
     *
     * @param key
     * @return
     */
    public long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 實現命令：KEYS pattern，查找所有符合給定模式 pattern的 key
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 實現命令：DEL key，刪除一個key
     *
     * @param key
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }

    // String（字符串）

    /**
     * 實現命令：SET key value，設置一個key-value（將字符串值 value關聯到 key）
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 實現命令：SET key value EX seconds，設置key-value和超時時間（秒）
     *
     * @param key
     * @param value
     * @param timeout （以秒為單位）
     */
    public void set(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 實現命令：GET key，返回 key所關聯的字符串值。
     *
     * @param key
     * @return value
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // Hash（哈希表）

    /**
     * 實現命令：HSET key field value，將哈希表 key中的域 field的值設為 value
     *
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 實現命令：HGET key field，返回哈希表 key中給定域 field的值
     *
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        return (String) redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 實現命令：HDEL key field [field ...]，刪除哈希表 key 中的一個或多個指定域，不存在的域將被忽略。
     *
     * @param key
     * @param fields
     */
    public void hdel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 實現命令：HGETALL key，返回哈希表 key中，所有的域和值。
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hgetall(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 判断给定key是否存在
     *
     * @param key
     * @return
     */
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    // List（列表）

    /**
     * 實現命令：LPUSH key value，將一個值 value插入到列表 key的表頭
     *
     * @param key
     * @param value
     * @return 執行 LPUSH命令後，列表的長度。
     */
    public long lpush(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 實現命令：LPOP key，移除並返回列表 key的頭元素。
     *
     * @param key
     * @return 列表key的頭元素。
     */
    public String lpop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 實現命令：RPUSH key value，將一個值 value插入到列表 key的表尾(最右邊)。
     *
     * @param key
     * @param value
     * @return 執行 LPUSH命令後，列表的長度。
     */
    public long rpush(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 根据openId获取redis中已登录用户信息
     *
     * @param openId
     * @return
     */
    public LoginUserInfo getLoginUserInfoByOpenId(String openId) {
        String loginUserInfoJson = get(GlobalConstParam.WECHAT_USER.concat("_").concat(openId));
        return GsonUtils.fromJson(loginUserInfoJson, LoginUserInfo.class);
    }

    /**
     * 根据openId设置redis中已登录用户信息
     *
     * @param openId
     * @return
     */
    public void setLoginUserInfoByOpenId(String openId, String loginUserInfoJson) {
        String loginUserInfoKey = GlobalConstParam.WECHAT_USER.concat("_").concat(openId);
        set(loginUserInfoKey, loginUserInfoJson);
        Integer expire = 12;
        if (systemInfo.getTokenExpire() != null) {
            expire = systemInfo.getTokenExpire();
        }
        expire(loginUserInfoKey, 60 * 60 * expire);
    }

}
