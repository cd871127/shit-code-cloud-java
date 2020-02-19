package com.shit_code.cloud.shiro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author Anthony Chen
 * @date 2020/2/19
 **/
@Resource
@RequestMapping("authorization")
public class AuthorizationController {
    @GetMapping("")
    String test() {
        return "";
    }
}
