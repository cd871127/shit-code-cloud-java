package com.shit_code.cloud.lib.springboot.redis;


import com.shit_code.cloud.lib.springboot.common.ShitLock;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author Anthony Chen
 * @date 2020/3/3
 **/
public class RedisLock implements ShitLock {

    public RedisLock(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.unlockScript = new DefaultRedisScript<>();
        this.unlockScript.setScriptText("if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end");
        unlockScript.setResultType(Boolean.class);
    }

    private RedisTemplate<String, String> redisTemplate;

    private DefaultRedisScript<Boolean> unlockScript;

    @Override
    public boolean tryLock(String lockName, String lockOwner, long expiration, long timeout) {
        long start = System.currentTimeMillis();
        boolean lockResult;
        do {
            lockResult = lock(lockName, lockOwner, expiration);
        } while (!lockResult && System.currentTimeMillis() - start <= timeout);
        return lockResult;
    }


    @Override
    public boolean lock(String lockName, String lockOwner, long expiration) {
        try {
            BoundValueOperations<String, String> redis = redisTemplate.boundValueOps(lockName(lockName));
            Boolean result;
            if (expiration != 0) {
                result = redis.setIfAbsent(lockOwner, expiration, TimeUnit.MILLISECONDS);
            } else {
                result = redis.setIfAbsent(lockOwner);
            }
            return result == null ? false : result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean unlock(String lockName, String lockOwner) {
        try {
            Boolean result = redisTemplate.execute(unlockScript, Collections.singletonList(lockName(lockName)), lockOwner);
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
