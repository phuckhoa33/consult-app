package com.consult_app.demo.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.consult_app.demo.models.Admin;

@Mapper
public interface AdminMapper {
    Admin getAdminByUserId(@Param("userId") String userId);

    Admin getAdminByEmail(@Param("email") String email);
}
