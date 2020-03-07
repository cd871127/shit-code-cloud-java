package com.shit_code.cloud.lib.springboot.redis;


import com.shit_code.cloud.lib.core.lock.ReentrantLockInfo;
import com.shit_code.cloud.lib.springboot.common.AbstractSpringReentrantLock;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Anthony Chen
 * @date 2020/3/3
 **/
public class RedisLock extends AbstractSpringReentrantLock<ReentrantLockInfo> {

    public RedisLock(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.unlockScript = new DefaultRedisScript<>();
        this.unlockScript.setScriptText("if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end");
        unlockScript.setResultType(Boolean.class);
    }

    private StringRedisTemplate stringRedisTemplate;

    private DefaultRedisScript<Boolean> unlockScript;

    private static ThreadLocal<Map<String, ReentrantLockInfo>> threadMap = ThreadLocal.withInitial(HashMap::new);

    @Override
    protected ReentrantLockInfo createLockInfo(String lockName, String lockValue, long expiration) {
        ReentrantLockInfo reentrantLockInfo = new ReentrantLockInfo().setExpiration(expiration).setLockName(lockName).setLockValue(lockValue);
        threadMap.get().put(lockName, reentrantLockInfo);
        return reentrantLockInfo;
    }

    @Override
    protected ReentrantLockInfo getLockInfo(String lockName) {
        return threadMap.get().get(lockName);
    }

    @Override
    protected boolean renew(ReentrantLockInfo reentrantLockInfo) {
        BoundValueOperations<String, String> redis = stringRedisTemplate.boundValueOps(lockName(reentrantLockInfo.getLockName()));
        //是自己的锁
        if (reentrantLockInfo.getLockValue().equals(redis.get())) {
            redis.expire(reentrantLockInfo.getExpiration(), TimeUnit.MILLISECONDS);
            return true;
        }
        return false;
    }

    @Override
    protected boolean acquire(ReentrantLockInfo reentrantLockInfo) {
        try {
            BoundValueOperations<String, String> redis = stringRedisTemplate.boundValueOps(lockName(reentrantLockInfo.getLockName()));
            Boolean result;
            if (reentrantLockInfo.getExpiration() != 0) {
                result = redis.setIfAbsent(reentrantLockInfo.getLockValue(), reentrantLockInfo.getExpiration(), TimeUnit.MILLISECONDS);
            } else {
                result = redis.setIfAbsent(reentrantLockInfo.getLockValue());
            }
            return result == null ? false : result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected boolean release(ReentrantLockInfo reentrantLockInfo) {
        try {
            Boolean result = stringRedisTemplate.execute(unlockScript,
                    Collections.singletonList(lockName(reentrantLockInfo.getLockName())), reentrantLockInfo.getLockValue());
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
