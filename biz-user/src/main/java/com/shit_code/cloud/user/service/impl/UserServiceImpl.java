package com.shit_code.cloud.user.service.impl;

import com.shit_code.cloud.user.dto.UserInfoDTO;
import com.shit_code.cloud.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Anthony
 */
@RestController
public class UserServiceImpl implements UserService {

    @Override
    public UserInfoDTO queryUserById() {
        return new UserInfoDTO("userId");
    }
}
