package com.shit_code.cloud.auth;

import com.shit_code.cloud.auth.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Anthony Chen
 * @date 2020/2/12
 **/
@RestController("/")
public class TestController {

    @Resource
    private UserService userService;

    @GetMapping("test")
    public String teset() {
        return userService.queryUserById().getUserId();
    }
}
