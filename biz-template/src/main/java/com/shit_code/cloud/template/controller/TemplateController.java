package com.shit_code.cloud.template.controller;

import com.shit_code.cloud.template.service.TemplateService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

}
