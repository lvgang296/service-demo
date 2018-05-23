package com.springboot.manager.service.manager;

import com.springboot.manager.bean.Dept;
import com.springboot.manager.mapper.DeptMapper;
import com.springboot.manager.utils.BeanMapperUtils;
import com.springboot.model.base.Tree;
import com.springboot.model.dto.DeptDto;
import com.springboot.model.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lvgang on 2018/5/22 12:48
 */
@Component
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class DeptManager {
    @Autowired
    private DeptMapper deptMapper;

    public Tree<DeptDto> getDeptTree() {
        List<Tree<DeptDto>> trees = new ArrayList<Tree<DeptDto>>();
        List<DeptDto> depts = deptMapper.findAllDepts(new DeptDto());
        for (DeptDto dept : depts) {
            Tree<DeptDto> tree = new Tree<DeptDto>();
            tree.setId(dept.getDeptId().toString());
            tree.setParentId(dept.getParentId().toString());
            tree.setText(dept.getDeptName());
            trees.add(tree);
        }
        Tree<DeptDto> t = TreeUtils.build(trees);
        return t;
    }

    public List<DeptDto> findAllDepts(DeptDto deptDto) {
        return deptMapper.findAllDepts(deptDto);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addDept(DeptDto deptDto) {
        Long parentId = deptDto.getParentId();
        if (parentId == null)
            deptDto.setParentId(0L);
        deptDto.setCreateTime(new Date());
        Dept dept = new Dept();
        BeanMapperUtils.copy(deptDto,dept);
        deptMapper.insert(dept);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateDept(DeptDto deptDto) {
        Dept dept = new Dept();
        BeanMapperUtils.copy(deptDto,dept);
        deptMapper.updateByPrimaryKeySelective(dept);

    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteDepts(List<Long> deptIds) {
        for(Long deptId : deptIds){
            deptMapper.deleteByPrimaryKey(deptId);
        }
        deptMapper.changeToTop(deptIds);
    }
}
