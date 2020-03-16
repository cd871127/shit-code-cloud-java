package com.shit_code.cloud.template.job;

import com.shit_code.cloud.template.service.TemplateService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Component
public class MessageJob {
    @Resource
    private TemplateService templateService;

    @Scheduled(fixedRate = 60L * 1000L)
    public void message() {
        templateService.sendMessage(LocalDateTime.now().toString());
    }
}
