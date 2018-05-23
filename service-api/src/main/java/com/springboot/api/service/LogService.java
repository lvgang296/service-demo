package com.springboot.api.service;

import com.github.pagehelper.PageInfo;
import com.springboot.model.base.Response;
import com.springboot.model.dto.LogDto;

/**
 * Created by lvgang on 2018/5/23 17:25
 */
public interface LogService {
    Response<PageInfo<LogDto>> findAllLog(LogDto dto);

    Response addLog(LogDto dto);

    Response deleteLogs(String logDtos);
}
