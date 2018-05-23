package com.springboot.manager.mapper;

import com.springboot.manager.bean.RoleMenu;

import java.util.List;

public interface RoleMenuMapper {
    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    int deleteRoleMenus(List<String> menuIds);

    int deleteRoleMenusByRoleId(Long roleId);
}