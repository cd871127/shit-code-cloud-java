package com.shit_code.cloud.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Anthony Chen
 * @date 2020/2/19
 **/
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Resource
    SecurityManager securityManager;

    @GetMapping("{userId}")
    String login(@PathVariable("userId") String userId) {
        SecurityUtils.setSecurityManager(securityManager);
        SecurityUtils.getSubject().login(new UsernamePasswordToken("111", "111"));

        return "1";
    }

}
