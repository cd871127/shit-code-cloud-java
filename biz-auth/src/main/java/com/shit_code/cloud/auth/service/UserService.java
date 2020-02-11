package com.shit_code.cloud.auth.service;


import com.shit_code.cloud.auth.dto.UserInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("biz-user")
public interface UserService {

    /**
     * @return
     */
    @GetMapping("/user")
    UserInfoDTO queryUserById();
}
