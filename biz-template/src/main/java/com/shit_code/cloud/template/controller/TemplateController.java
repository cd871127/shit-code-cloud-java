package com.shit_code.cloud.template.controller;

import com.shit_code.cloud.template.mapper.TestDAO;
import com.shit_code.cloud.template.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.LongStream;

@Slf4j
@RestController
@RequestMapping("/template")
public class TemplateController {

    @Resource
    private TemplateService templateService;

    @Value("${spring.cloud.client.ip-address}")
    private String ip;


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
    String testThread() {
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

    @GetMapping
    String getIp() {
        return ip;
    }

    @Resource
    private TestDAO testDAO;

    @GetMapping("insert/{num}")
    String insert(@PathVariable("num") long num) throws InterruptedException, ExecutionException {
        String result = "";
        long start;
        long end;

//        start = System.currentTimeMillis();
//        sequentialSum(num);
//        end = System.currentTimeMillis();
//        result += "串行流：" + (end - start) + "<br>";
//        testDAO.delete();


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 8,
                60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        List<Future<Integer>> futures = new LinkedList<>();
        start = System.currentTimeMillis();
        for (long i = 0; i < num; ++i) {
            final long j = i;
            futures.add(threadPoolExecutor.submit(() -> testDAO.insert(j)));
        }
        for (Future<Integer> future : futures) {
            future.get();
        }
        end = System.currentTimeMillis();
        threadPoolExecutor.shutdown();
        testDAO.delete();
        result += "线程池：" + (end - start) + "<br>";

        start = System.currentTimeMillis();
        parallelSum(num);
        end = System.currentTimeMillis();
        result += "并行流：" + (end - start) + "<br>";
        testDAO.delete();
        return result;
    }

    public void sequentialSum(long n) {
        LongStream.range(0, n).forEach((num) -> testDAO.insert(num));
    }

    public void parallelSum(long n) {
        LongStream.range(0, n)
                .parallel()
                .forEach((num) -> testDAO.insert(num));
    }
}
