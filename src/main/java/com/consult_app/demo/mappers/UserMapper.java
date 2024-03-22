package com.consult_app.demo.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.consult_app.demo.models.User;

@Mapper
public interface UserMapper {
    void createUser(User user);

    User getUserByUserName(@Param("username") String username);

    User getUserByEmail(@Param("email") String email);

    User getUserByUserId(@Param("userId") int userId);

    void updateUser(User user);

}
