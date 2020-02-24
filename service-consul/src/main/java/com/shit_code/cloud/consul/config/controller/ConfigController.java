package com.shit_code.cloud.consul.config.controller;

import com.shit_code.cloud.consul.config.loader.LocalConfigLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Anthony Chen
 * @date 2020/2/16
 **/
@RestController
@RequestMapping("/config")
@Slf4j
public class ConfigController {

    @Resource
    private LocalConfigLoader localConfigLoader;

    @GetMapping("test")
    String test() {
        localConfigLoader.loadConfig();
        return "";
    }

    @GetMapping("log")
    String refresh() {
        log.info("Info 日志");
        log.debug("Debug 日志");
        log.warn("warn 日志");
        log.error("error 日志");
        return "ok";
    }

}
