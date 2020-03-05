package com.shit_code.cloud.lib.springboot.common;

/**
 * @author Anthony Chen
 * @date 2020/3/6
 **/
public interface ShitLock {
    /**
     * 上锁
     *
     * @param lockName  锁的名字
     * @param lockOwner 所有者
     * @return true 成功获得锁 false 失败
     */
    default boolean lock(String lockName, String lockOwner) {
        return lock(lockName, lockOwner, 0);
    }

    /**
     * 上锁
     *
     * @param lockName   lockName  锁的名字
     * @param lockOwner  lockOwner 所有者
     * @param expiration 锁的有效期
     * @return true 成功获得锁 false 失败
     */
    boolean lock(String lockName, String lockOwner, long expiration);

    /**
     * 上锁
     *
     * @param lockName  lockName  锁的名字
     * @param lockOwner lockOwner 所有者
     * @param timeout   超时时间
     * @return true 成功获得锁 false 失败
     */
    default boolean tryLock(String lockName, String lockOwner, long timeout) {
        return tryLock(lockName, lockOwner, 0, timeout);
    }

    /**
     * 上锁
     *
     * @param lockName   lockName  锁的名字
     * @param lockOwner  lockOwner 所有者
     * @param expiration 锁的有效期
     * @param timeout    超时时间
     * @return true 成功获得锁 false 失败
     */
    boolean tryLock(String lockName, String lockOwner, long expiration, long timeout);

    /**
     * 解锁
     *
     * @param lockName  lockName  锁的名字
     * @param lockOwner lockOwner 所有者
     * @return true 成功解锁 false 失败
     */
    boolean unlock(String lockName, String lockOwner);
}
