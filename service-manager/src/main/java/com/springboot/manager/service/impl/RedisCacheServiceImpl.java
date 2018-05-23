package com.springboot.manager.service.impl;

import com.springboot.api.service.RedisCacheService;
import com.springboot.manager.service.manager.RedisCacheManager;
import com.springboot.model.base.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lvgang on 2018/5/1 13:49
 */
@Service("redisCacheService")
public class RedisCacheServiceImpl implements RedisCacheService {
    @Autowired
    private RedisCacheManager redisCacheManager;
    @Override
    public Response<Boolean> loadCache() {
        redisCacheManager.loadCache();
        return new Response<>(true);
    }
}
