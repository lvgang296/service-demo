package com.springboot.manager.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.springboot.api.service.LogService;
import com.springboot.manager.enums.ResponseCodeEnum;
import com.springboot.manager.service.manager.LogSysManager;
import com.springboot.manager.utils.ServiceDemoException;
import com.springboot.model.base.Response;
import com.springboot.model.dto.LogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lvgang on 2018/5/23 17:36
 */
@Service("logService")
@Slf4j
public class LogServiceImpl implements LogService {
    @Autowired
    private LogSysManager logSysManager;
    @Override
    public Response<PageInfo<LogDto>> findAllLog(LogDto dto) {
        log.info("request findAllLog,param:{}", dto);
        Response<PageInfo<LogDto>> resp = new Response<>();
        try {
            PageInfo<LogDto> logDtos = logSysManager.findAllLog(dto);
            resp.setResult(logDtos);
            log.info("success to findAllLog!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to findAllLog,parameter:{}, RESULT:{}", dto, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to findAllLog,PARAMETER:{}, CAUSE:{}", dto, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response addLog(LogDto dto) {
        log.info("request addLog,param:{}", dto);
        Response resp = new Response();
        try {
            logSysManager.addLog(dto);
            resp.setSuccess(true);
            log.info("success to addLog!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to addLog,parameter:{}, RESULT:{}", dto, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to addLog,PARAMETER:{}, CAUSE:{}", dto, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }

    @Override
    public Response deleteLogs(String logDtos) {
        log.info("request deleteLogs,param:{}", logDtos);
        Response resp = new Response();
        try {
            logSysManager.deleteLogs(logDtos);
            resp.setSuccess(true);
            log.info("success to deleteLogs!result:{}", resp);
        }catch (ServiceDemoException se){
            resp.setErrorCode(se.getCode());
            resp.setErrorMsg(se.getMessage());
            log.info("failed to deleteLogs,parameter:{}, RESULT:{}", logDtos, resp);
        }catch (Exception e){
            resp.setErrorCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getCode());
            resp.setErrorMsg(ResponseCodeEnum.INTERNAL_SERVER_ERROR_500.getDesc());
            log.error("failed to deleteLogs,PARAMETER:{}, CAUSE:{}", logDtos, Throwables.getStackTraceAsString(e));
        }
        return resp;
    }
}
