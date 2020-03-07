package com.shit_code.cloud.lib.core.lock;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class AbstractReentrantLockTest {

    @Test
    public void testIp() throws UnknownHostException {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName(); // format: "pid@hostname"
        System.out.println(Thread.currentThread().getId());
        InetAddress.getLocalHost();
        InetAddress ia = null;
        try {
            ia = ia.getLocalHost();

            String localname = ia.getHostName();
            String localip = ia.getHostAddress();
            System.out.println("本机名称是：" + localname);
            System.out.println("本机的ip是 ：" + localip);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}