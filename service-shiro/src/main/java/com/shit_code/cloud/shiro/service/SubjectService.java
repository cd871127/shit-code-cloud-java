package com.shit_code.cloud.shiro.service;

import com.shit_code.cloud.shiro.mapper.SubjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Anthony Chen
 * @date 2020/2/23
 **/
@Slf4j
@Service
public class SubjectService {
    @Resource
    private SubjectMapper subjectMapper;


}
