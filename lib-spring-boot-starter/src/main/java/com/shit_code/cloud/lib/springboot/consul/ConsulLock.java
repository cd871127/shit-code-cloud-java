package com.shit_code.cloud.lib.springboot.consul;


import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.ecwid.consul.v1.kv.model.PutParams;
import com.ecwid.consul.v1.session.model.NewSession;
import com.ecwid.consul.v1.session.model.Session;
import com.shit_code.cloud.lib.springboot.common.AbstractSpringReentrantLock;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anthony Chen
 * @date 2020/3/6
 **/
@NoArgsConstructor
@AllArgsConstructor
public class ConsulLock extends AbstractSpringReentrantLock<ConsulLockInfo> {

    private ConsulClient consulClient;

    private final static String CONSUL_LOCK_ROOT = "lock/";

    private static ThreadLocal<Map<String, ConsulLockInfo>> threadMap = ThreadLocal.withInitial(HashMap::new);

    @Override
    protected ConsulLockInfo createLockInfo(String lockName, String lockValue, long expiration) {
        String sessionId = createSession(lockName, expiration);
        ConsulLockInfo consulLockInfo = new ConsulLockInfo().setLockName(lockName).setExpiration(expiration)
                .setLockValue(lockValue).setSessionId(sessionId);
        threadMap.get().put(lockName, consulLockInfo);
        return consulLockInfo;
    }


    @Override
    protected ConsulLockInfo getLockInfo(String lockName) {
        return threadMap.get().get(lockName);
    }

    @Override
    protected boolean renew(ConsulLockInfo consulLockInfo) {
        Session session = consulClient.renewSession(consulLockInfo.getSessionId(), null).getValue();
        if (consulLockInfo.getSessionId().equals(session.getId())) {
            return acquire(consulLockInfo);
        }
        return false;
    }

    @Override
    protected boolean acquire(ConsulLockInfo consulLockInfo) {
        PutParams putParams = new PutParams();
        putParams.setAcquireSession(consulLockInfo.getSessionId());
        //判断value是否相等
        GetValue getValue = consulClient.getKVValue(CONSUL_LOCK_ROOT + consulLockInfo.getLockName()).getValue();
        if (getValue == null || consulLockInfo.getLockValue().equals(getValue.getDecodedValue())) {
            return consulClient.setKVValue(CONSUL_LOCK_ROOT + consulLockInfo.getLockName(),
                    consulLockInfo.getLockValue(), putParams).getValue();
        }
        return false;
    }

    @Override
    protected boolean release(ConsulLockInfo consulLockInfo) {
        //删掉key
        consulClient.deleteKVValue(CONSUL_LOCK_ROOT + consulLockInfo.getLockName());
        //销毁session
        consulClient.sessionDestroy(consulLockInfo.getSessionId(), null);
        //清理现场
        threadMap.get().remove(consulLockInfo.getLockName());
        if (threadMap.get().isEmpty()) {
            threadMap.remove();
        }
        return true;
    }

    /**
     * 创建consul session
     *
     * @param lockName
     * @param expiration
     * @return
     */
    private String createSession(String lockName, long expiration) {
        NewSession session = new NewSession();
        session.setBehavior(Session.Behavior.RELEASE);
        session.setName("session " + lockName);
        session.setLockDelay(1);
        session.setTtl(expiration / 100L + "s");
        return consulClient.sessionCreate(session, null).getValue();
    }


}
