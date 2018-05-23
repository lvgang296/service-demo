package com.springboot.manager.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.springboot.api.service.UserService;
import com.springboot.manager.enums.ResponseCodeEnum;
import com.springboot.manager.service.manager.UserManager;
import com.springboot.manager.utils.ServiceDemoException;
import com.springboot.model.base.Response;
import com.springboot.model.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by lvgang on 2018/4/30 1:15
 */
@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserManager userManager;
    @Override
    public Response<UserDto> login(String username , String password) {
        log.info("request login,accountName:{}", username);
        Response<UserDto> resp = new Response<>();
        try {
            UserDto userDto = userManager.login(username,password);
            resp.setResult(userDto);
            log.info("success to login!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to login,parameter:{}, RESULT:{}", username, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to login,PARAMETER:{}, CAUSE:{}", username, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }


    @Override
    public Response<PageInfo<UserDto>> queryAllUser(UserDto dto) {
        log.info("request queryAllUser,param:{}", dto);
        Response<PageInfo<UserDto>> resp = new Response<>();
        try {
            PageInfo<UserDto> userDtos = userManager.queryAllUser(dto);
            resp.setResult(userDtos);
            log.info("success to queryAllUser!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to queryAllUser,parameter:{}, RESULT:{}", dto, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to queryAllUser,PARAMETER:{}, CAUSE:{}", dto, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response registUser(UserDto userDto) {
        log.info("request registUser,param:{}", userDto);
        Response resp = new Response();
        try {
            userManager.registUser(userDto);
            resp.setSuccess(true);
            log.info("success to registUser!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to registUser,parameter:{}, RESULT:{}", userDto, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to registUser,PARAMETER:{}, CAUSE:{}", userDto, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response updateUser(UserDto userDto, List<Long> roleIds) {
        log.info("request updateUser,paramUserDto:{},paramRoleIds:{}", userDto,roleIds);
        Response resp = new Response();
        try {
            userManager.updateUser(userDto,roleIds);
            resp.setSuccess(true);
            log.info("success to updateUser!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to updateUser,paramUserDto:{}, paramRoleIds:{},RESULT:{}", userDto,roleIds, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to updateUser,paramUserDto:{},paramRoleIds:{}, CAUSE:{}", userDto, roleIds,Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response deleteUser(List<Long> userIds) {
        log.info("request deleteUser,param:{}", userIds);
        Response resp = new Response();
        try {
            userManager.deleteUser(userIds);
            resp.setSuccess(true);
            log.info("success to deleteUser!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to deleteUser,parameter:{}, RESULT:{}", userIds, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to deleteUser,PARAMETER:{}, CAUSE:{}", userIds, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }
}
