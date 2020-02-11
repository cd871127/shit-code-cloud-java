package com.shit_code.cloud.user.service.impl;

import com.shit_code.cloud.user.dto.UserInfoDTO;
import com.shit_code.cloud.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Anthony
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserInfoDTO queryUserById(String userId) {
        return new UserInfoDTO(userId);
    }
}
