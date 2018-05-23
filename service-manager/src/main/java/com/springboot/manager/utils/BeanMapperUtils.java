package com.springboot.manager.utils;


import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by lvgang on 2018/5/1.
 */
public class BeanMapperUtils {

    private static DozerBeanMapper dozer = new DozerBeanMapper();

    private BeanMapperUtils() {
    }

    /**
     * 基于Dozer转换对象的类型.
     */
    public static <T> T map ( Object source, Class <T> destinationClass ) {
        return dozer.map(source, destinationClass);
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     */
    public static <T> List <T> mapList ( Collection sourceList, Class <T> destinationClass ) {
        List <T> destinationList = new ArrayList();
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy ( Object source, Object destinationObject ) {
        dozer.map(source, destinationObject);
    }
}
