package com.shit_code.cloud.lib.springboot.consul;

import com.shit_code.cloud.lib.core.lock.ReentrantLockInfo;

/**
 * @author Anthony Chen
 * @date 2020/3/7
 **/
public class ConsulLockInfo extends ReentrantLockInfo {
    private String sessionId;


    public String getSessionId() {
        return sessionId;
    }

    public ConsulLockInfo setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    @Override
    public ConsulLockInfo setLockName(String lockName) {
        super.setLockName(lockName);
        return this;
    }

    @Override
    public ConsulLockInfo setLockValue(String lockValue) {
        super.setLockValue(lockValue);
        return this;
    }

    @Override
    public ConsulLockInfo setExpiration(long expiration) {
        super.setExpiration(expiration);
        return this;
    }
}
