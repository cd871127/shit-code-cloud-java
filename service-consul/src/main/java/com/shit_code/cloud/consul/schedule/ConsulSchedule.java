package com.shit_code.cloud.consul.schedule;

import com.shit_code.cloud.consul.config.ConfigManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Anthony Chen
 * @date 2020/3/6
 **/
@Component
@Slf4j
public class ConsulSchedule {
    @Resource
    private ConfigManager configManager;

    @Scheduled(fixedRate = 2000)
    public void config() {
        log.debug("配置任务开始");
        configManager.pushConfig(false);
        log.debug("配置任务结束");
    }
}
