package com.springboot.api.service;

import com.springboot.model.base.Response;

/**
 * Created by lvgang on 2018/5/1 13:49
 */
public interface RedisCacheService {
    Response<Boolean> loadCache ();
}
