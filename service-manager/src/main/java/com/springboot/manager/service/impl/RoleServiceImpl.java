package com.springboot.manager.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.springboot.api.service.RoleService;
import com.springboot.manager.enums.ResponseCodeEnum;
import com.springboot.manager.service.manager.RoleManager;
import com.springboot.manager.utils.ServiceDemoException;
import com.springboot.model.base.Response;
import com.springboot.model.dto.RoleDto;
import com.springboot.model.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lvgang on 2018/5/20 10:38
 */
@Service("roleService")
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleManager roleManager;
    @Override
    public Response<PageInfo<RoleDto>> findAllRole(RoleDto dto) {
        log.info("request findAllRole,param:{}", dto);
        Response<PageInfo<RoleDto>> resp = new Response<>();
        try {
            PageInfo<RoleDto> roleDtos = roleManager.findAllRole(dto);
            resp.setResult(roleDtos);
            log.info("success to findAllRole!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to findAllRole,parameter:{}, RESULT:{}", dto, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to findAllRole,PARAMETER:{}, CAUSE:{}", dto, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response<List<RoleDto>> findUserRole(Long userId) {
        log.info("request findUserRole,param:{}", userId);
        Response<List<RoleDto>> resp = new Response<>();
        try {
            List<RoleDto> roleDtos = roleManager.findUserRole(userId);
            resp.setResult(roleDtos);
            log.info("success to findUserRole!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to findUserRole,parameter:{}, RESULT:{}", userId, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to findUserRole,PARAMETER:{}, CAUSE:{}", userId, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response addRole(RoleDto dto, List<Long> menuIds) {
        log.info("request addRole,roleDtopParam:{},menuIdsParam:{}", dto,menuIds);
        Response resp = new Response();
        try {
            roleManager.addRole(dto,menuIds);
            resp.setSuccess(true);
            log.info("success to addRole!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to addRole,roleDtopParam:{},menuIdsParam:{}, RESULT:{}", dto,menuIds, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to addRole,roleDtopParam:{},menuIdsParam:{}, CAUSE:{}", dto,menuIds, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response updateRole(RoleDto dto, List<String> menuIds) {
        log.info("request updateRole,roleDtopParam:{},menuIdsParam:{}", dto,menuIds);
        Response resp = new Response();
        try {
            roleManager.updateRole(dto,menuIds);
            resp.setSuccess(true);
            log.info("success to updateRole!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to updateRole,roleDtopParam:{},menuIdsParam:{}, RESULT:{}", dto,menuIds, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to updateRole,roleDtopParam:{},menuIdsParam:{}, CAUSE:{}", dto,menuIds, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response deleteRoles(String roleIds) {
        log.info("request deleteRoles,param:{}", roleIds);
        Response resp = new Response();
        try {
            roleManager.deleteRoles(roleIds);
            resp.setSuccess(true);
            log.info("success to deleteRoles!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to deleteRoles,parameter:{}, RESULT:{}", roleIds, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to deleteRoles,PARAMETER:{}, CAUSE:{}", roleIds, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }
}
