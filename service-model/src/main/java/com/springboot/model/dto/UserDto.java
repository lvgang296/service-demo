package com.springboot.model.dto;

import com.springboot.model.base.BaseDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UserDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 1044015656208270246L;
    public static final String DEFAULT_THEME = "green";

    public static final String DEFAULT_AVATAR = "default.jpg";
    private Long userId;

    private String username;

    private String password;

    private Long deptId;

    private String email;

    private String mobile;

    private String status;

    private Date crateTime;

    private Date modifyTime;

    private Date lastLoginTime;

    private String ssex;

    private String theme;

    private String avatar;

    private String description;

    private String deptName;

    private List<RoleDto> roleDtos;


}