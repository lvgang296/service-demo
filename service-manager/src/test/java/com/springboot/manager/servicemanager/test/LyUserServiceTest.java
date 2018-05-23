package com.springboot.manager.servicemanager.test;

import com.github.pagehelper.PageInfo;
import com.springboot.api.service.RedisCacheService;
import com.springboot.api.service.UserService;
import com.springboot.manager.servicemanager.ServiceManagerApplicationTests;
import com.springboot.model.base.Response;
import com.springboot.model.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lvgang on 2018/5/1 0:58
 */
@Slf4j
public class LyUserServiceTest extends ServiceManagerApplicationTests {

    @Autowired
    private RedisCacheService redisCacheService;
    @Autowired
    private UserService userService;
    @Test
    public void testQueryAllUserInfo(){
        UserDto dto = new UserDto();
        Response<PageInfo<UserDto>> pageInfoResponse = userService.queryAllUser(dto);
        log.info(""+pageInfoResponse.getResult().getTotal());
    }

    @Test
    public void testLoadCache(){
        redisCacheService.loadCache();
    }
}
