package com.springboot.manager.service.manager;

import com.springboot.manager.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lvgang on 2018/5/1 13:50
 */
@Component
@Slf4j
public class RedisCacheManager {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 调用自定义线程池异步执行
     */
    @Async("customerExecutor")
    public void loadCache() {
        log.info("加载缓存数据开始！");
        Map map = new HashMap();
        map.put("name","zhangshan");
        RedisUtils.setHashMap("lvgang",map);
        log.info("加载缓存数据结束！");
    }
}
