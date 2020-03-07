package com.shit_code.cloud.lib.springboot.common;

import com.shit_code.cloud.lib.core.lock.AbstractReentrantLock;
import com.shit_code.cloud.lib.core.lock.ReentrantLockInfo;
import org.springframework.beans.factory.annotation.Value;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Anthony Chen
 * @date 2020/3/7
 **/
public abstract class AbstractSpringReentrantLock<T extends ReentrantLockInfo> extends AbstractReentrantLock<T> {


    @Value("${server.port:UNKNOWN_PORT}")
    private int port;

    /**
     * 创建新的lockInfo
     *
     * @param lockName
     * @param expiration
     * @return
     */
    @Override
    protected T createLockInfo(String lockName, long expiration) {
        String lockValue = ipAddr() + "_" + port + "_" + processId() + "_" + threadId();
        return createLockInfo(lockName, lockValue, expiration);
    }

    /**
     * 创建新的lockInfo
     *
     * @param lockName
     * @param lockValue
     * @param expiration
     * @return
     */
    protected abstract T createLockInfo(String lockName, String lockValue, long expiration);

    /**
     * 获取进程id
     *
     * @return
     */
    private String processId() {
        return ManagementFactory.getRuntimeMXBean().getName();
    }

    /**
     * 获取线程id
     *
     * @return
     */
    private long threadId() {
        return Thread.currentThread().getId();
    }

    private String ipAddr() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "KNOWN_ADDR";
        }
    }
}
