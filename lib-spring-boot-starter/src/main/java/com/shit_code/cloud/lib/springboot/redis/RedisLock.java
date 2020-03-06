package com.shit_code.cloud.lib.springboot.redis;


import com.shit_code.cloud.lib.core.lock.ShitCodeLock;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author Anthony Chen
 * @date 2020/3/3
 **/
public class RedisLock implements ShitCodeLock {

    public RedisLock(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.unlockScript = new DefaultRedisScript<>();
        this.unlockScript.setScriptText("if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end");
        unlockScript.setResultType(Boolean.class);
    }

    private RedisTemplate<String, String> redisTemplate;

    private DefaultRedisScript<Boolean> unlockScript;

    @Override
    public boolean lock(String lockName, String lockValue, long expiration) {
        try {
            BoundValueOperations<String, String> redis = redisTemplate.boundValueOps(lockName(lockName));
            Boolean result;
            if (expiration != 0) {
                result = redis.setIfAbsent(lockValue, expiration, TimeUnit.MILLISECONDS);
            } else {
                result = redis.setIfAbsent(lockValue);
            }
            return result == null ? false : result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean unlock(String lockName, String lockValue) {
        try {
            Boolean result = redisTemplate.execute(unlockScript, Collections.singletonList(lockName(lockName)), lockValue);
            return result == null ? false : result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String lockName(String lockName) {
        return "Lock." + lockName;
    }

}
