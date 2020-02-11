package com.shit_code.cloud.user.service;


import com.shit_code.cloud.user.dto.UserInfoDTO;

public interface UserService {

    /**
     * @param userId
     * @return
     */
    UserInfoDTO queryUserById(String userId);
}
