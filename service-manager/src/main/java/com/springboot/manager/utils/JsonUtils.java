package com.springboot.manager.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lvgang on 2018/5/1 0:42
 */
public class JsonUtils {
    private Logger log = LoggerFactory.getLogger(JsonUtils.class);
    private static ObjectMapper objectMapper = null;

    static {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss") {
            });
        }
    }

    private JsonUtils () {

    }

    public static ObjectMapper getInstance () {

        return objectMapper;
    }

    /**
     * javaBean,list,array convert to json string
     */
    public static String obj2json ( Object obj ) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeJsonMappingException("wrong parameter!");
        }
    }

    /**
     * json string convert to javaBean
     */
    public static <T> T json2pojo ( String jsonStr, Class <T> clazz ) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeJsonMappingException("IOException!");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * json string convert to map
     */
    public static <T> Map<String, Object> json2map (String jsonStr ) {
        try {
            return objectMapper.readValue(jsonStr, Map.class);
        } catch (IOException e) {
            throw new RuntimeJsonMappingException("IOException!");
        }
    }

    /**
     * json string convert to map with javaBean
     */
    public static <T> Map <String, T> json2map ( String jsonStr, Class <T> clazz ) {

        Map <String, Map <String, Object>> map = null;
        try {
            map = objectMapper.readValue(jsonStr,
                    new TypeReference<Map <String, T>>() {
                    });
        } catch (IOException e) {
            throw new RuntimeJsonMappingException("IOException!");
        }
        Map <String, T> result = new HashMap<String, T>();
        for (Map.Entry <String, Map <String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * json array string convert to list with javaBean
     */
    public static <T> List<T> json2list (String jsonArrayStr, Class <T> clazz ) {
        List <Map <String, Object>> list = null;
        try {
            list = objectMapper.readValue(jsonArrayStr,
                    new TypeReference <List <T>>() {
                    });
            List <T> result = new ArrayList<T>();
            for (Map <String, Object> map : list) {
                result.add(map2pojo(map, clazz));
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeJsonMappingException("IOException！");
        }
    }

    /**
     * map convert to javaBean
     */
    public static <T> T map2pojo ( Map map, Class <T> clazz ) {
        return objectMapper.convertValue(map, clazz);
    }
}
