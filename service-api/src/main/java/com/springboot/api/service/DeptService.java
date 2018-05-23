package com.springboot.api.service;

import com.github.pagehelper.PageInfo;
import com.springboot.model.base.Response;
import com.springboot.model.base.Tree;
import com.springboot.model.dto.DeptDto;

import java.util.List;

/**
 * Created by lvgang on 2018/5/22 12:30
 */
public interface DeptService {
    Response<Tree<DeptDto>> getDeptTree();

    Response<List<DeptDto>> findAllDepts(DeptDto deptDto);

    Response addDept(DeptDto deptDto);

    Response updateDept(DeptDto deptDto);

    Response deleteDepts(List<Long> deptIds);
}
