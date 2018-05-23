package com.springboot.api.service;

import com.springboot.model.base.Response;
import com.springboot.model.base.Tree;
import com.springboot.model.dto.MenuDto;
import com.springboot.model.dto.RoleDto;

import java.util.List;

/**
 * Created by lvgang on 2018/5/20 10:02
 */
public interface MenuService {

    Response<List<MenuDto>> queryMenuByUserId(Long userId);

    Response<List<MenuDto>> queryMenuByRoleId(Long roleId);

    Response<List<MenuDto>> findAllMenu(MenuDto dto);

    Response addMenu(MenuDto dto);

    Response updateMenu(MenuDto dto);

    Response deleteMeuns(String menuIds);

    Response<Tree<MenuDto>> getMenuTree(MenuDto menuDto);

    Response<Tree<MenuDto>> getUserMenu(Long userId);
}
