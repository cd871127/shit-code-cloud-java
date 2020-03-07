package com.shit_code.cloud.consul.schedule.aop;

import com.shit_code.cloud.lib.springboot.consul.ConsulLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Anthony Chen
 * @date 2020/3/6
 **/
//@Component
@Aspect
@Slf4j
public class ScheduleAspect {

    @Resource
    private ConsulLock consulLock;

    @Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public void schedule() {
    }

    @Around("schedule()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        Object res = null;
        String lockName = proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." + proceedingJoinPoint.getSignature().getName();
        try {
            if (consulLock.lock(lockName)) {
                log.debug("Get lock: {}", lockName);
                res = proceedingJoinPoint.proceed();
            } else {
                log.debug("Fail to get lock: {}", lockName);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            consulLock.unlock(lockName);
            log.debug("Release lock: {}", lockName);
        }
        return res;
    }
}
