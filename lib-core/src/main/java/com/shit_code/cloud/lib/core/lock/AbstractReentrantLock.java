package com.shit_code.cloud.lib.core.lock;

import com.shit_code.cloud.lib.core.exception.ShitCodeException;

/**
 * @author Anthony Chen
 * @date 2020/3/6
 **/
public abstract class AbstractReentrantLock<T extends ReentrantLockInfo> implements ShitCodeLock {

    @Override
    public boolean lock(String lockName, long expiration) {
        T lockInfo = getLockInfo(lockName);
        if (lockInfo != null && renew(lockInfo)) {
            //重入
            lockInfo.add();
            return true;
        } else if (lockInfo == null) {
            //第一次进入
            lockInfo = createLockInfo(lockName, expiration);
            if (acquire(lockInfo)) {
                lockInfo.add();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean unlock(String lockName) {
        T lockInfo = getLockInfo(lockName);
        if (lockInfo == null) {
            throw new ShitCodeException("Didn't have lock: " + lockName);
        }
        if (lockInfo.count() > 1) {
            lockInfo.minus();
            return true;
        } else if (lockInfo.count() == 1) {
            return release(lockInfo);
        } else {
            throw new ShitCodeException("error");
        }
    }

    /**
     * 创建新的lockInfo
     *
     * @param lockName
     * @param expiration
     * @return
     */
    protected abstract T createLockInfo(String lockName, long expiration);

    /**
     * 从已有的lockinfo中获取
     *
     * @param lockName
     * @return
     */
    protected abstract T getLockInfo(String lockName);

    /**
     * 刷新锁
     *
     * @param lockInfo
     * @return
     */
    protected abstract boolean renew(T lockInfo);

    /**
     * 获取锁
     *
     * @param lockInfo
     * @return
     */
    protected abstract boolean acquire(T lockInfo);

    /**
     * 释放锁
     *
     * @param lockInfo
     * @return
     */
    protected abstract boolean release(T lockInfo);


}
