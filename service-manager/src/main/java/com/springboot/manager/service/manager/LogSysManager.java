package com.springboot.manager.service.manager;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.manager.bean.Log;
import com.springboot.manager.mapper.LogMapper;
import com.springboot.manager.utils.BeanMapperUtils;
import com.springboot.model.dto.LogDto;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lvgang on 2018/5/23 17:40
 */
@Component
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class LogSysManager {
    @Autowired
    private LogMapper logMapper;
    public PageInfo<LogDto> findAllLog(LogDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<LogDto> logDtos = logMapper.findAllLog(dto);
        return new PageInfo <LogDto>(logDtos);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addLog(LogDto dto) {
        Log log = new Log();
        BeanMapperUtils.copy(dto,log);
        logMapper.insertSelective(log);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteLogs(String logDtos) {
        for(String id : Arrays.asList(logDtos.split(","))){
            logMapper.deleteByPrimaryKey(Long.parseLong(id));
        }
    }
}
