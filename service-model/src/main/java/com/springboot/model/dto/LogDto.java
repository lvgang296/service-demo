package com.springboot.model.dto;

import com.springboot.model.base.BaseDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LogDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 9017775766084816040L;
    private Long id;

    private String username;

    private Long time;

    private String ip;

    private Date createTime;

    private String location;

    private String operation;

    private String method;

    private String params;


}