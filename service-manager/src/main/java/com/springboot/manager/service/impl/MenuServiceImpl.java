package com.springboot.manager.service.impl;

import com.google.common.base.Throwables;
import com.springboot.api.service.MenuService;
import com.springboot.manager.enums.ResponseCodeEnum;
import com.springboot.manager.service.manager.MenuManager;
import com.springboot.manager.utils.ServiceDemoException;
import com.springboot.model.base.Response;
import com.springboot.model.base.Tree;
import com.springboot.model.dto.MenuDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lvgang on 2018/5/20 10:38
 */
@Service("menuService")
@Slf4j
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuManager menuManager;
    @Override
    public Response<List<MenuDto>> queryMenuByUserId(Long userId) {
        log.info("request queryMenuByUserId,param:{}", userId);
        Response<List<MenuDto>> resp = new Response<>();
        try {
            List<MenuDto> menuDtos = menuManager.queryMenuByUserId(userId);
            resp.setResult(menuDtos);
            log.info("success to queryMenuByUserId!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to queryMenuByUserId,parameter:{}, RESULT:{}", userId, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to queryMenuByUserId,PARAMETER:{}, CAUSE:{}", userId, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response<List<MenuDto>> queryMenuByRoleId(Long roleId) {
        log.info("request queryMenuByRoleId,param:{}", roleId);
        Response<List<MenuDto>> resp = new Response<>();
        try {
            List<MenuDto> menuDtos = menuManager.queryMenuByRoleId(roleId);
            resp.setResult(menuDtos);
            log.info("success to queryMenuByRoleId!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to queryMenuByRoleId,parameter:{}, RESULT:{}", roleId, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to queryMenuByRoleId,PARAMETER:{}, CAUSE:{}", roleId, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response<List<MenuDto>> findAllMenu(MenuDto dto) {
        log.info("request findAllMenu,param:{}", dto);
        Response<List<MenuDto>> resp = new Response<>();
        try {
            List<MenuDto> menuDtos = menuManager.findAllMenu(dto);
            resp.setResult(menuDtos);
            log.info("success to findAllMenu!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to findAllMenu,parameter:{}, RESULT:{}", dto, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to findAllMenu,PARAMETER:{}, CAUSE:{}", dto, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response addMenu(MenuDto dto) {
        log.info("request addMenu,param:{}", dto);
        Response resp = new Response();
        try {
            menuManager.addMenu(dto);
            resp.setSuccess(true);
            log.info("success to addMenu!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to addMenu,parameter:{}, RESULT:{}", dto, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to addMenu,PARAMETER:{}, CAUSE:{}", dto, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response updateMenu(MenuDto dto) {
        log.info("request updateMenu,param:{}", dto);
        Response resp = new Response();
        try {
            menuManager.updateMenu(dto);
            resp.setSuccess(true);
            log.info("success to updateMenu!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to updateMenu,parameter:{}, RESULT:{}", dto, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to updateMenu,PARAMETER:{}, CAUSE:{}", dto, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response deleteMeuns(String menuIds) {
        log.info("request deleteMeuns,param:{}", menuIds);
        Response resp = new Response();
        try {
            menuManager.deleteMeuns(menuIds);
            resp.setSuccess(true);
            log.info("success to deleteMeuns!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to deleteMeuns,parameter:{}, RESULT:{}", menuIds, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to deleteMeuns,PARAMETER:{}, CAUSE:{}", menuIds, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response<Tree<MenuDto>> getMenuTree(MenuDto menuDto) {
        log.info("request getMenuTree,param:{}", menuDto);
        Response<Tree<MenuDto>> resp = new Response<>();
        try {
            Tree<MenuDto> menuDtos = menuManager.getMenuTree(menuDto);
            resp.setResult(menuDtos);
            log.info("success to getMenuTree!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to getMenuTree,parameter:{}, RESULT:{}", menuDto, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to getMenuTree,PARAMETER:{}, CAUSE:{}", menuDto, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response<Tree<MenuDto>> getUserMenu(Long userId) {
        log.info("request getUserMenu,param:{}", userId);
        Response<Tree<MenuDto>> resp = new Response<>();
        try {
            Tree<MenuDto> menuDtos = menuManager.getUserMenu(userId);
            resp.setResult(menuDtos);
            log.info("success to getUserMenu!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to getUserMenu,parameter:{}, RESULT:{}", userId, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to getUserMenu,PARAMETER:{}, CAUSE:{}", userId, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

}
