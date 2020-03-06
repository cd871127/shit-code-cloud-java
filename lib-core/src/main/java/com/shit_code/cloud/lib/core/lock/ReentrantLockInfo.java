package com.shit_code.cloud.lib.core.lock;

import com.shit_code.cloud.lib.core.exception.ShitCodeException;

/**
 * @author Anthony Chen
 * @date 2020/3/6
 **/
public class ReentrantLockInfo {

    private int num = 0;

    private String lockName;
    private String lockValue;
    private long expiration;

    public String getLockName() {
        return lockName;
    }

    public ReentrantLockInfo setLockName(String lockName) {
        this.lockName = lockName;
        return this;
    }

    public String getLockValue() {
        return lockValue;
    }

    public ReentrantLockInfo setLockValue(String lockValue) {
        this.lockValue = lockValue;
        return this;
    }

    public long getExpiration() {
        return expiration;
    }

    public ReentrantLockInfo setExpiration(long expiration) {
        this.expiration = expiration;
        return this;
    }

    /**
     * 重入数加1
     *
     * @return
     */
    int add() {
        ++num;
        return count();
    }

    /**
     * 重入数-1
     *
     * @return
     */
    int minus() {
        --num;
        return count();
    }

    /**
     * 统计重入数
     *
     * @return
     */
    int count() {
        if (num < 0) {
            throw new ShitCodeException("error");
        }
        return num;
    }
}
