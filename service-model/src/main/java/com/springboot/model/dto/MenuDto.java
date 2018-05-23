package com.springboot.model.dto;

import com.springboot.model.base.BaseDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lvgang on 2018/5/20 9:47
 */
@Data
public class MenuDto extends BaseDto implements Serializable  {
    private static final long serialVersionUID = -3408146993845494431L;
    private Long menuId;

    private Long parentId;

    private String menuName;

    private String url;

    private String icon;

    private String type;

    private Long orderNum;

    private Date createTime;

    private Date modifyTime;

    private String perms;
}
