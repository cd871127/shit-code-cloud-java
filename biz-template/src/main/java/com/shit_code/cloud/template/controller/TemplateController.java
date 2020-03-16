package com.shit_code.cloud.template.controller;

import com.shit_code.cloud.template.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@RestController
@RequestMapping("/template")
public class TemplateController {

    @Resource
    private TemplateService templateService;


    @PostMapping("cache/{key}")
    String cache(@PathVariable("key") String key, @RequestBody String value) {
        templateService.setCache(key, value.toString(), 300L * 1000L);
        return "ok";
    }

    @GetMapping("cache/{key}")
    String cache(@PathVariable("key") String key) {
        return templateService.getCache(key);
    }

    @GetMapping("message/{body}")
    String sendMessage(@PathVariable("body") String body) {
        templateService.sendMessage(body);
        return "ok";
    }

    @GetMapping("test/thread")
    String testThread()  {
        log.info("controller traceId");
        Thread thread = new Thread(() -> log.info("thread traceId"));
        thread.start();
        templateService.threadLog();

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(20);
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix("testaa");

        // 线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        executor.execute(() -> log.info("execute thread traceId"));

        return "ok";
    }
}
