package com.shit_code.cloud.lib.springboot.consul;

import com.shit_code.cloud.lib.springboot.common.ShitLock;

/**
 * @author Anthony Chen
 * @date 2020/3/6
 **/
public class ConsulLock implements ShitLock {

    @Override
    public boolean lock(String lockName, String lockOwner, long expiration) {
        return false;
    }

    @Override
    public boolean tryLock(String lockName, String lockOwner, long expiration, long timeout) {
        return false;
    }

    @Override
    public boolean unlock(String lockName, String lockOwner) {
        return false;
    }
}
