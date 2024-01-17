package com.consult_app.demo.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.consult_app.demo.models.User;

@Mapper
public interface UserMapper {
    User getUserByUserName(@Param("username") String username);
}
