package com.shit_code.cloud.shiro.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Anthony Chen
 * @date 2020/2/23
 **/
@Mapper
public interface SubjectMapper {

    /**
     * 添加
     *
     * @return
     */
    int add();

    /**
     * @return
     */
    int del();

    /**
     * @return
     */
    int update();

    /**
     * @return
     */
    int find();

}
