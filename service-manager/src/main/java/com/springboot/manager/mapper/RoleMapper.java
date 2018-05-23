package com.springboot.manager.mapper;

import com.springboot.manager.bean.Role;
import com.springboot.model.dto.RoleDto;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> findUserRole(Long userId);

    List<RoleDto> findAllRole(RoleDto dto);
}