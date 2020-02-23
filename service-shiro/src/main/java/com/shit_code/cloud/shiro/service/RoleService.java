package com.shit_code.cloud.shiro.service;

import com.shit_code.cloud.shiro.mapper.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Anthony Chen
 * @date 2020/2/23
 **/
@Slf4j
@Service
public class RoleService {
    @Resource
    private RoleMapper roleMapper;


}
