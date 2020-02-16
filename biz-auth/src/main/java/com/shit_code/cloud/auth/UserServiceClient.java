package com.shit_code.cloud.auth;

import com.shit_code.cloud.user.service.UserService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Anthony Chen
 * @date 2020/2/15
 **/
@FeignClient("biz-user")
public interface UserServiceClient extends UserService {
}
