package com.shit_code.cloud.user.service;


import com.shit_code.cloud.user.dto.UserInfoDTO;
import org.springframework.web.bind.annotation.GetMapping;

public interface UserService {

    /**
     * @return
     */
    @GetMapping("/user")
    UserInfoDTO queryUserById();
}
