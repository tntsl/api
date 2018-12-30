package com.demo.api.common.util;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Redis distributed lock implementation.
 *
 * @author zhengcanrui
 */
@Service
public class RedisLockService {

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 锁超时时间，防止线程在入锁以后，无限的执行等待
     */
    private int lockExpireTime = 5;
    /**
     * 锁等待时间，防止线程饥饿
     */
    private int lockWaitTime = 10 * 1000;

    private Boolean setNX(final String key, final String value) {
        Boolean result = redisTemplate.opsForValue().setIfAbsent(key, value);
        redisTemplate.expire(key, lockExpireTime, TimeUnit.SECONDS);
        return result;
    }

    /**
     * 获得 lock.
     * 实现思路: 主要是使用了redis 的setnx命令,缓存了锁.
     * reids缓存的key是锁的key,所有的共享, value是锁的到期时间(注意:这里把过期时间放在value了,没有时间上设置其超时时间)
     * 执行过程:
     * 1.通过setnx尝试设置某个key的值,成功(当前没有这个锁)则返回,成功获得锁
     * 2.锁已经存在则获取锁的到期时间,和当前时间比较,超时的话,则设置新的值
     *
     * @return true if lock is acquired, false acquire timeouted
     * @throws InterruptedException in case of thread interruption
     */
    public String lock(String lockKey) throws InterruptedException {
        synchronized (RedisLockService.class) {
            int delayTime = new Random().nextInt(500);
            int timeout = lockWaitTime;
            while (timeout >= 0) {
                long expires = System.currentTimeMillis() + lockExpireTime + 1;
                //锁到期时间
                String expipreTime = String.valueOf(expires);
                //如果设置成功则获取到锁，失败则继续判断
                if (this.setNX(lockKey, expipreTime)) {
                    redisTemplate.expire(lockKey, lockExpireTime, TimeUnit.SECONDS);
                    return expipreTime;
                }
                timeout -= delayTime;
                RedisLockService.class.wait(delayTime);
            }
            return null;
        }
    }

    /**
     * 解锁，需提供正确的value值
     */
    public boolean unlock(String lockKey, String lockValue) {
        String redisStoreValue = (String) redisTemplate.opsForValue().get(lockKey);
        if (StringUtils.isNotBlank(lockValue) && lockValue.equals(redisStoreValue)) {
            redisTemplate.delete(lockKey);
            return true;
        }
        return false;
    }

}
