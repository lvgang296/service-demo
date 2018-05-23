package com.springboot.manager.service.manager;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.manager.bean.Role;
import com.springboot.manager.bean.RoleMenu;
import com.springboot.manager.mapper.RoleMapper;
import com.springboot.manager.mapper.RoleMenuMapper;
import com.springboot.manager.mapper.UserRoleMapper;
import com.springboot.manager.utils.BeanMapperUtils;
import com.springboot.model.dto.RoleDto;
import com.springboot.model.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by lvgang on 2018/5/20 10:41
 */
@Component
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class RoleManager {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    public List<RoleDto> findUserRole(Long userId) {
        List<Role> roles = roleMapper.findUserRole(userId);
        List<RoleDto> roleDtos = BeanMapperUtils.mapList(roles,RoleDto.class);
        return roleDtos;
    }

    public PageInfo<RoleDto> findAllRole(RoleDto dto) {
        //设置分页参数
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<RoleDto> roleDtos = roleMapper.findAllRole(dto);
        return new PageInfo <RoleDto>(roleDtos);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteRoles(String roleIds) {
        List<String> list = Arrays.asList(roleIds.split(","));
        for(String roleId : list){
            roleMapper.deleteByPrimaryKey(Long.parseLong(roleId));
            roleMenuMapper.deleteRoleMenusByRoleId(Long.parseLong(roleId));
            userRoleMapper.deleteUserRoleByRoleId(Long.parseLong(roleId));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateRole(RoleDto dto, List<String> menuIds) {
        dto.setModifyTime(new Date());
        Role role = new Role();
        BeanMapperUtils.copy(dto,role);
        roleMapper.updateByPrimaryKeySelective(role);
        roleMenuMapper.deleteRoleMenus(menuIds);
        if(ObjectUtils.isNotNullAndEmpty(menuIds)){
            for (String menuId : menuIds) {
                RoleMenu rm = new RoleMenu();
                rm.setMenuId(Long.parseLong(menuId));
                rm.setRoleId(role.getRoleId());
                this.roleMenuMapper.insert(rm);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void addRole(RoleDto dto, List<Long> menuIds) {
        dto.setCreateTime(new Date());
        Role role = new Role();
        BeanMapperUtils.copy(dto,role);
        roleMapper.insert(role);
        for (Long menuId : menuIds) {
            RoleMenu rm = new RoleMenu();
            rm.setMenuId(menuId);
            rm.setRoleId(role.getRoleId());
            roleMenuMapper.insert(rm);
        }
    }
}
