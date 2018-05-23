package com.springboot.model.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lvgang on 2018/5/20 16:26
 */
@Data
public class BaseDto implements Serializable {
    private static final long serialVersionUID = 6169463053657730879L;
    private int pageSize;
    private int pageNum;
    private Date beginTime;
    private Date endTime;
}
