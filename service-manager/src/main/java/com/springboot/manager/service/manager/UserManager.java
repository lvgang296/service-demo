package com.springboot.manager.service.manager;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.manager.bean.Menu;
import com.springboot.manager.bean.User;
import com.springboot.manager.bean.UserRole;
import com.springboot.manager.mapper.UserMapper;
import com.springboot.manager.mapper.UserRoleMapper;
import com.springboot.manager.utils.BeanMapperUtils;
import com.springboot.manager.utils.ServiceDemoException;
import com.springboot.model.dto.UserDto;
import com.springboot.model.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by lvgang on 2018/5/1 0:35
 */
@Component
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserManager {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;



    public UserDto login(String username, String password) {
        User param = new User();
        param.setUsername(username);
        param.setPassword(password);
        User user = userMapper.login(param);
        UserDto dto = new UserDto();
        if(!Objects.isNull(user)){
            BeanMapperUtils.copy(user,dto);
        }else{
            throw new ServiceDemoException("1111","用户名或密码错误！");
        }
        return dto;
    }


    public PageInfo<UserDto> queryAllUser(UserDto dto) {
        //设置分页参数
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<UserDto> userDtos = userMapper.queryAllUser(dto);
        return new PageInfo <UserDto>(userDtos);
    }

    @Transactional(rollbackFor = Exception.class)
    public void registUser(UserDto userDto) {
        userDto.setCrateTime(new Date());
        userDto.setTheme(UserDto.DEFAULT_THEME);
        userDto.setAvatar(UserDto.DEFAULT_AVATAR);
        User user = new User();
        BeanMapperUtils.copy(userDto,user);
        userMapper.insert(user);
        UserRole ur = new UserRole();
        ur.setUserId(user.getUserId());
        ur.setRoleId(3L);
        userRoleMapper.insert(ur);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserDto userDto, List<Long> roleIds) {
        userDto.setModifyTime(new Date());
        User user = new User();
        BeanMapperUtils.copy(userDto,user);
        userMapper.updateByPrimaryKeySelective(user);
        userRoleMapper.deleteUserRoleByUserId(userDto.getUserId());
        userRoleMapper.deleteUserRoleByUserId(userDto.getUserId());
        if(ObjectUtils.isNotNullAndEmpty(roleIds)){
            for (Long roleId : roleIds) {
                UserRole ur = new UserRole();
                ur.setUserId(user.getUserId());
                ur.setRoleId(roleId);
                userRoleMapper.insert(ur);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(List<Long> userIds) {
        for (Long userId : userIds) {
            userMapper.deleteByPrimaryKey(userId);
            userRoleMapper.deleteUserRoleByUserId(userId);
        }
    }
}
