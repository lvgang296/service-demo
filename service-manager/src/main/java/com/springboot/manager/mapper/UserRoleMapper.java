package com.springboot.manager.mapper;

import com.springboot.manager.bean.UserRole;

import java.util.List;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

    int deleteUserRoleByUserId(Long userId);

    int deleteUserRoleByRoleId(Long roleId);
}