package com.shit_code.cloud.lib.core.lock;

/**
 * @author Anthony Chen
 * @date 2020/3/6
 **/
public interface ShitCodeLock {

    /**
     * 默认30秒
     */
    long DEFAULT_EXPIRATION = 30000L;

    /**
     * 上锁
     *
     * @param lockName lockName  锁的名字
     * @return true 成功获得锁 false 失败
     */
    default boolean lock(String lockName) {
        return lock(lockName, DEFAULT_EXPIRATION);
    }

    /**
     * 上锁
     *
     * @param lockName   lockName  锁的名字
     * @param expiration 锁的有效期
     * @return true 成功获得锁 false 失败
     */
    boolean lock(String lockName, long expiration);

    /**
     * 解锁
     *
     * @param lockName lockName  锁的名字
     * @return true 成功解锁 false 失败
     */
    boolean unlock(String lockName);

    /**
     * 尝试上锁
     *
     * @param lockName 锁的名字
     * @param timeout  超时时间
     * @return true 成功获得锁 false 失败
     */
    default boolean tryLock(String lockName, long timeout) {
        return tryLock(lockName, DEFAULT_EXPIRATION, timeout);
    }

    /**
     * 尝试上锁
     *
     * @param lockName   锁的名字
     * @param expiration 锁的有效期
     * @param timeout    超时时间
     * @return true 成功获得锁 false 失败
     */
    default boolean tryLock(String lockName, long expiration, long timeout) {
        long start = System.currentTimeMillis();
        boolean lockResult;
        do {
            lockResult = lock(lockName, expiration);
        } while (!lockResult && System.currentTimeMillis() - start <= timeout);
        return lockResult;
    }

//    /**
//     * 上锁
//     *
//     * @param lockName  锁的名字
//     * @param lockValue 所有者
//     * @return true 成功获得锁 false 失败
//     */
//    @Deprecated
//    default boolean lock(String lockName, String lockValue) {
//        return lock(lockName, lockValue, 5000);
//    }
//
//    /**
//     * 上锁
//     *
//     * @param lockName   lockName  锁的名字
//     * @param lockValue  lockValue 所有者
//     * @param expiration 锁的有效期
//     * @return true 成功获得锁 false 失败
//     */
//    @Deprecated
//    boolean lock(String lockName, String lockValue, long expiration);
//
//    /**
//     * 上锁
//     *
//     * @param lockName  lockName  锁的名字
//     * @param lockValue lockValue 所有者
//     * @param timeout   超时时间
//     * @return true 成功获得锁 false 失败
//     */
//    @Deprecated
//    default boolean tryLock(String lockName, String lockValue, long timeout) {
//        return tryLock(lockName, lockValue, 5000, timeout);
//    }
//
//    /**
//     * 上锁
//     *
//     * @param lockName   lockName  锁的名字
//     * @param lockValue  lockValue 所有者
//     * @param expiration 锁的有效期
//     * @param timeout    超时时间
//     * @return true 成功获得锁 false 失败
//     */
//    @Deprecated
//    default boolean tryLock(String lockName, String lockValue, long expiration, long timeout) {
//        long start = System.currentTimeMillis();
//        boolean lockResult;
//        do {
//            lockResult = lock(lockName, lockValue, expiration);
//        } while (!lockResult && System.currentTimeMillis() - start <= timeout);
//        return lockResult;
//    }
//
//    /**
//     * 解锁
//     *
//     * @param lockName  lockName  锁的名字
//     * @param lockValue lockValue 所有者
//     * @return true 成功解锁 false 失败
//     */
//    @Deprecated
//    boolean unlock(String lockName, String lockValue);
}
