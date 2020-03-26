package com.shit_code.cloud.lib.springboot.log.aspect;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class MonitorLogAspect {

    private static final Gson gson = new Gson();

    @Pointcut("((@within(org.springframework.web.bind.annotation.RestController)" +
            "||@within(org.springframework.stereotype.Controller))" +
            "&&(@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.PatchMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.DeleteMapping)))" +
            "||@within(com.shit_code.cloud.lib.springboot.log.MonitorLog)" +
            "||@annotation(com.shit_code.cloud.lib.springboot.log.MonitorLog)")
    public void monitorLog() {
    }

    @Around("monitorLog()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        try {
            var signature = proceedingJoinPoint.getSignature();
            var className = proceedingJoinPoint.getSignature().getDeclaringTypeName();
            var methodName = signature.getName();
            var stringBuilder = new StringBuilder();
            stringBuilder.append("Start execute: ").append(className).append("#")
                    .append(methodName);
            var prefixLength = stringBuilder.length();
            stringBuilder.append(" parameters:").append(gson.toJson(proceedingJoinPoint.getArgs()));

            log.info(stringBuilder.toString());
            var start = System.nanoTime();
            Object result = proceedingJoinPoint.proceed();
            var end = System.nanoTime();

            stringBuilder.setLength(prefixLength);
            stringBuilder.append(" result: ").append(gson.toJson(result)).append(" elapse: ")
                    .append(end - start).append("ms");
            stringBuilder.replace(0, 5, "Finish");
            log.info(stringBuilder.toString());
            return result;
        } catch (Throwable throwable) {
            log.error(throwable.getMessage());
            throw throwable;
        }
    }
}
