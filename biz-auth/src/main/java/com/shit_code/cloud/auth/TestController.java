package com.shit_code.cloud.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Anthony Chen
 * @date 2020/2/12
 **/
@RestController("/")
public class TestController {

//    @Resource
//    private UserServiceClient userService;

    @GetMapping("test")
    public String teset() {
//        return userService.queryUserById().getUserId();
        return "";
    }
}
