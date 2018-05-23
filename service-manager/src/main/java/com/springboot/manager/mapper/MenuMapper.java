package com.springboot.manager.mapper;

import com.springboot.manager.bean.Menu;
import com.springboot.model.dto.MenuDto;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Long menuId);

    int deleteMenus(List<String> menuIds);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKeyWithBLOBs(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> findAllMenu(MenuDto dto);

    List<Menu> queryMenuByUserId(Long userId);

    List<Menu> queryMenuByRoleId(Long roleId);

    void changeToTop(List<String> list);
}