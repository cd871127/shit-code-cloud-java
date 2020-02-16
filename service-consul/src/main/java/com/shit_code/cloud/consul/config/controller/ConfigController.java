package com.shit_code.cloud.consul.config.controller;

import com.shit_code.cloud.consul.config.loader.LocalConfigLoader;
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
public class ConfigController {

    @Resource
    private LocalConfigLoader localConfigLoader;

    @GetMapping("test")
    String test() {
        localConfigLoader.loadConfig();
        return "";
    }

}
