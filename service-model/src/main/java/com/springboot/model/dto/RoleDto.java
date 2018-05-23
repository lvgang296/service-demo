package com.springboot.model.dto;

import com.springboot.model.base.BaseDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lvgang on 2018/5/20 9:47
 */
@Data
public class RoleDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 6417801717364624092L;
    private Long roleId;

    private String roleName;

    private String remark;

    private Date createTime;

    private Date modifyTime;

    private List<MenuDto> menuDtos;
}
