package com.springboot.manager.service.manager;

import com.springboot.manager.bean.Menu;
import com.springboot.manager.mapper.MenuMapper;
import com.springboot.manager.mapper.RoleMenuMapper;
import com.springboot.manager.utils.BeanMapperUtils;
import com.springboot.model.base.Tree;
import com.springboot.model.dto.MenuDto;
import com.springboot.model.enummodel.MenuTypeEnum;
import com.springboot.model.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by lvgang on 2018/5/20 10:53
 */
@Component
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MenuManager {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    public List<MenuDto> findAllMenu(MenuDto dto) {
        List<Menu> menus = menuMapper.findAllMenu(dto);
        List<MenuDto> menuDtos = BeanMapperUtils.mapList(menus,MenuDto.class);
        return menuDtos;
    }

    public List<MenuDto> queryMenuByUserId(Long userId) {
        List<Menu> menus = menuMapper.queryMenuByUserId(userId);
        List<MenuDto> menuDtos = BeanMapperUtils.mapList(menus,MenuDto.class);
        return menuDtos;
    }

    public List<MenuDto> queryMenuByRoleId(Long roleId) {
        List<Menu> menus = menuMapper.queryMenuByRoleId(roleId);
        List<MenuDto> menuDtos = BeanMapperUtils.mapList(menus,MenuDto.class);
        return menuDtos;
    }

    @Transactional(rollbackFor = Exception.class)
    public void addMenu(MenuDto dto) {
        dto.setCreateTime(new Date());
        if (dto.getParentId() == null){
            dto.setParentId(0l);
        }
        Menu menu = new Menu();
        BeanMapperUtils.copy(dto,menu);
        menuMapper.insert(menu);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateMenu(MenuDto dto) {
        dto.setModifyTime(new Date());
        if (dto.getParentId() == null){
            dto.setParentId(0l);
        }
        Menu menu = new Menu();
        BeanMapperUtils.copy(dto,menu);
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteMeuns(String menuIds) {
        List<String> list = Arrays.asList(menuIds.split(","));
        menuMapper.deleteMenus(list);
        roleMenuMapper.deleteRoleMenus(list);
        menuMapper.changeToTop(list);
    }

    public Tree<MenuDto> getUserMenu(Long userId) {
        List<Tree<MenuDto>> trees = new ArrayList<Tree<MenuDto>>();
        List<Menu> menus = menuMapper.queryMenuByUserId(userId);
        List<MenuDto> menuDtos = BeanMapperUtils.mapList(menus,MenuDto.class);
        for (MenuDto menu : menuDtos) {
            if(MenuTypeEnum.MENU.getCode().equals(menu.getType())){
                Tree<MenuDto> tree = new Tree<MenuDto>();
                tree.setId(menu.getMenuId().toString());
                tree.setParentId(menu.getParentId().toString());
                tree.setText(menu.getMenuName());
                tree.setIcon(menu.getIcon());
                tree.setUrl(menu.getUrl());
                trees.add(tree);
            }

        }
        Tree<MenuDto> t = TreeUtils.build(trees);
        return t;
    }

    public Tree<MenuDto> getMenuTree(MenuDto menuDto) {
        List<Tree<MenuDto>> trees = new ArrayList<Tree<MenuDto>>();
        List<Menu> menus = menuMapper.findAllMenu(menuDto);
        List<MenuDto> menuDtos = BeanMapperUtils.mapList(menus,MenuDto.class);
        for (MenuDto menu : menuDtos) {
            Tree<MenuDto> tree = new Tree<MenuDto>();
            tree.setId(menu.getMenuId().toString());
            tree.setParentId(menu.getParentId().toString());
            tree.setText(menu.getMenuName());
            trees.add(tree);
        }
        Tree<MenuDto> t = TreeUtils.build(trees);
        return t;
    }
}
