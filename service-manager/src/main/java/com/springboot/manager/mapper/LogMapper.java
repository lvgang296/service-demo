package com.springboot.manager.mapper;

import com.springboot.manager.bean.Log;
import com.springboot.model.dto.LogDto;

import java.util.List;

public interface LogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKeyWithBLOBs(Log record);

    int updateByPrimaryKey(Log record);

    List<LogDto> findAllLog(LogDto dto);
}