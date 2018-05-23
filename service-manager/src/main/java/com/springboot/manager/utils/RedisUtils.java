package com.springboot.manager.utils;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by lvgang on 2018/5/1 13:03
 */
@Component
public class RedisUtils {
    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    private static RedisTemplate redisTemplates;

    private static RedisUtils redisUtils;

    @PostConstruct
    public void init () {
        RedisUtils.redisTemplates = redisTemplate;
    }

    private RedisUtils() {
    }

    static {
        if (null == redisUtils) {
            redisUtils = new RedisUtils();
        }
    }

    public static RedisUtils getInstance() {
        return redisUtils;
    }

    /**
     * 在Redis中放入值
     *
     * @param key
     * @param value
     */
    public static void setRedis(String key, Object value) {
        redisTemplates.opsForValue().set(key, value);
        redisTemplates.expire(key, 30, TimeUnit.SECONDS);
    }

    /**
     * 在Redis中放入值，并指定有效时间
     *
     * @param key
     * @param value
     * @param expire
     * @param timeUnit
     */
    public static void setRedis(String key, Object value, long expire, TimeUnit timeUnit) {
        redisTemplates.opsForValue().set(key, value);
        if (expire > 0) {
            redisTemplates.expire(key, expire, timeUnit);
        }
    }

    public static void setHashMap(String key, Map<String, String> map) {
        HashOperations hashOper = redisTemplates.opsForHash();
        hashOper.putAll(key, map);
    }

    /**
     * 删除Redis中的数据
     *
     * @param key
     */
    public static void removeRedis(String key) {
        redisTemplates.delete(key);
    }

    /**
     * 获取Redis中值的有效期
     *
     * @param key
     */
    public static Long getRedisExpire(String key) {
        return redisTemplates.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 从Redis中获取值
     * @param key
     * @return
     */
    public static Object getRedis(String key) {
        return redisTemplates.opsForValue().get(key);
    }

    /**
     * 从Redis中获取值，并重置有效期
     *
     * @param key
     * @return
     */
    public static Object getRedisResetExpire(String key) {
        Object sessionObj = redisTemplates.opsForValue().get(key);
        if (sessionObj != null) {
            //重置有效时间
            redisTemplates.expire(key, 30, TimeUnit.SECONDS);
        }
        return sessionObj;
    }

    /**
     * 根据表达式，获取所有键的集合
     *
     * @param pattern 表达式格式，如：key_*
     * @return
     */
    public static Set<String> getRedisKeys(String pattern) {
        Set<String> keys = Sets.newConcurrentHashSet();
        Set<byte[]> byteKeys = redisTemplates.getConnectionFactory().getConnection().keys(pattern.getBytes());
        Iterator<byte[]> it = byteKeys.iterator();
        while (it.hasNext()) {
            byte[] data = it.next();
            keys.add(new String(data, 0, data.length));
        }
        return keys;
    }

    /**
     * 一次获取多个值
     *
     * @param keys
     * @return
     */
    public static Collection<Object> getRedisMulti(String... keys) {
        if (keys == null) {
            return Collections.emptySet();
        }
        Collection<Object> collection = new ArrayList<>();
        for (int i = 0; i < keys.length; i++) {
            collection.add(keys[i]);
        }
        return redisTemplates.opsForValue().multiGet(collection);
    }

    /**
     * 根据Key获取已经保存到Redis中所有的值,Session级别
     *
     * @param key 键
     * @return map数据
     */
    public static Map<String, String> getAllMap(String key) {
        if ("".equals(key) || (null == key)) {
            return null;
        }
        Set<Object> keys = redisTemplates.keys("*_" + key);
        Map<String, String> map = new ConcurrentHashMap<>();
        if (keys != null && keys.size() > 0) {
            for (Object hashKey : keys) {
                String text = getValue(String.valueOf(hashKey), key);
                map.put(String.valueOf(hashKey), text);
            }
        }
        return map;
    }

    /**
     * 根据Key和HashKey获取一个值，返回String
     *
     * @param key     键
     * @param hashKey has键
     * @return String 缓存数据
     */
    public static String getValue(String key, String hashKey) {
        Object obj = redisTemplates.opsForHash().get(key, hashKey);
        return obj == null ? null : obj.toString();
    }


    public Map<String, String> getHashMap(String hashKey) {
        if (Strings.isNullOrEmpty(hashKey)) {
            return new HashMap();
        }
        return  redisTemplate.boundHashOps(hashKey).entries();
    }
}
