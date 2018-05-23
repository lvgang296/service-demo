package com.springboot.model.dto;

import com.springboot.model.base.BaseDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DeptDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 6949239941163025145L;
    private Long deptId;

    private Long parentId;

    private String deptName;

    private Long orderNum;

    private Date createTime;

}