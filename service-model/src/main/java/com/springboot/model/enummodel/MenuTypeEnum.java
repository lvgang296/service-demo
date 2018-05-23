package com.springboot.model.enummodel;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lvgang on 2018/5/20 15:04
 */
public enum MenuTypeEnum {

    MENU("0","菜单"),
    BUTTOM("1","按钮");
    @Getter
    private final String code;

    @Getter
    private final String desc;

    MenuTypeEnum(String code, String value) {
        this.code = code;
        this.desc = value;
    }

    public static String explain(String code){
        for (MenuTypeEnum menuTypeEnum : MenuTypeEnum.values()) {
            if(menuTypeEnum.code.equals(code)){
                return menuTypeEnum.getDesc();
            }
        }
        return null;
    }

    public static List<Map> toList() {
        List<Map> list = new ArrayList<Map>();
        for (MenuTypeEnum menuTypeEnum : MenuTypeEnum.values()) {
            Map map = new HashMap();
            map.put("code", menuTypeEnum.getCode());
            map.put("desc", menuTypeEnum.getDesc());
            list.add(map);
        }
        return list;
    }
}
