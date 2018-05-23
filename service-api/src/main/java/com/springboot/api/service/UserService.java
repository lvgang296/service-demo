package com.springboot.api.service;

import com.github.pagehelper.PageInfo;
import com.springboot.model.base.Response;
import com.springboot.model.dto.UserDto;

import java.util.List;

public interface UserService{

	Response<UserDto> login(String username ,String password);

	Response<PageInfo<UserDto>> queryAllUser(UserDto dto);

	Response registUser(UserDto userDto);

	Response updateUser(UserDto userDto,List<Long> roleIds);

	Response deleteUser(List<Long> userIds);

}
