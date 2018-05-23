package com.springboot.manager.service.impl;

import com.google.common.base.Throwables;
import com.springboot.api.service.DeptService;
import com.springboot.manager.enums.ResponseCodeEnum;
import com.springboot.manager.service.manager.DeptManager;
import com.springboot.manager.utils.ServiceDemoException;
import com.springboot.model.base.Response;
import com.springboot.model.base.Tree;
import com.springboot.model.dto.DeptDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lvgang on 2018/5/22 12:35
 */
@Service("deptService")
@Slf4j
public class DeptServiceImp implements DeptService {
    @Autowired
    private DeptManager deptManager;
    @Override
    public Response<Tree<DeptDto>> getDeptTree() {
        log.info("request getMenuTree", "");
        Response<Tree<DeptDto>> resp = new Response<>();
        try {
            Tree<DeptDto> deptDtoTree = deptManager.getDeptTree();
            resp.setResult(deptDtoTree);
            log.info("success to getDeptTree!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to getDeptTree,RESULT:{}", resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to getDeptTree, CAUSE:{}",  Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response<List<DeptDto>> findAllDepts(DeptDto deptDto) {
        log.info("request findAllDepts,param:{}", deptDto);
        Response<List<DeptDto>> resp = new Response<>();
        try {
            List<DeptDto> deptDtos = deptManager.findAllDepts(deptDto);
            resp.setResult(deptDtos);
            log.info("success to findAllDepts!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to findAllDepts,parameter:{}, RESULT:{}", deptDto, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to findAllDepts,PARAMETER:{}, CAUSE:{}", deptDto, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response addDept(DeptDto deptDto) {
        log.info("request addDept,param:{}", deptDto);
        Response resp = new Response();
        try {
            deptManager.addDept(deptDto);
            resp.setSuccess(true);
            log.info("success to addDept!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to addDept,parameter:{}, RESULT:{}", deptDto, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to addDept,PARAMETER:{}, CAUSE:{}", deptDto, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response updateDept(DeptDto deptDto) {
        log.info("request updateDept,param:{}", deptDto);
        Response resp = new Response();
        try {
            deptManager.updateDept(deptDto);
            resp.setSuccess(true);
            log.info("success to updateDept!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to updateDept,parameter:{}, RESULT:{}", deptDto, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to updateDept,PARAMETER:{}, CAUSE:{}", deptDto, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response deleteDepts(List<Long> deptIds) {
        log.info("request deleteDepts,param:{}", deptIds);
        Response resp = new Response();
        try {
            deptManager.deleteDepts(deptIds);
            resp.setSuccess(true);
            log.info("success to deleteDepts!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to deleteDepts,parameter:{}, RESULT:{}", deptIds, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to deleteDepts,PARAMETER:{}, CAUSE:{}", deptIds, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }
}
