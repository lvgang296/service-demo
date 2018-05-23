package com.springboot.manager.mapper;

import com.springboot.manager.bean.Job;

public interface JobMapper {
    int deleteByPrimaryKey(Long jobId);

    int insert(Job record);

    int insertSelective(Job record);

    Job selectByPrimaryKey(Long jobId);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);
}