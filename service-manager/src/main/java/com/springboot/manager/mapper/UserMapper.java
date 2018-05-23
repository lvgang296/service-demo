package com.springboot.manager.mapper;

import com.springboot.manager.bean.User;
import com.springboot.model.dto.UserDto;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User login(User param);

    List<UserDto> queryAllUser(UserDto userDto);
}