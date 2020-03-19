package com.shit_code.cloud.template.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TestDAO {

    @Insert("insert into test_sql.test_insert(value) value(#{value})")
    int insert(@Param("value") long value);

    @Delete("delete from test_sql.test_insert where 1=1")
    int delete();
}
