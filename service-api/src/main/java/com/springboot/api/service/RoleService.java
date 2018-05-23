package com.springboot.api.service;

import com.github.pagehelper.PageInfo;
import com.springboot.model.base.Response;
import com.springboot.model.dto.RoleDto;

import java.util.List;

/**
 * Created by lvgang on 2018/5/20 9:55
 */
public interface RoleService {

    Response<PageInfo<RoleDto>> findAllRole(RoleDto dto);

    Response<List<RoleDto>> findUserRole(Long userId);

    Response addRole(RoleDto role, List<Long> menuIds);

    Response updateRole(RoleDto role, List<String> menuIds);

    Response deleteRoles(String roleIds);
}
