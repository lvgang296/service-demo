package com.springboot.manager.mapper;

import com.springboot.manager.bean.Dept;
import com.springboot.model.dto.DeptDto;

import java.util.List;

public interface DeptMapper {
    int deleteByPrimaryKey(Long deptId);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Long deptId);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

    List<DeptDto> findAllDepts(DeptDto deptDto);

    void changeToTop(List<Long> deptIds);
}