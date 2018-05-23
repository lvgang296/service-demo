package com.springboot.manager.enums;

import lombok.Getter;

/**
 * Created by lvgang on 2018/4/30 3:06
 * 接口响应码
 */
public enum ResponseCodeEnum {

    /**
     * 如果一个业务中包含多个步骤，可在每个分步骤完成后返回此状态码
     */
    CONTINUE_100("100", "continue!"),

    /**
     * 请求处理成功
     */
    SUCCESS_200("200", "OK!"),


    /**
     * 没有权限
     */
    UNAUTHORIZED("401", "Unauthorized"),

    /**
     * 请求的资源未找到
     */
    NOT_FOUND_404("404", "resource not found!"),

    /**
     * 方法不被允许
     */
    METHOD_NOT_ALLOWED("405", "Method Not Allowed"),

    /**
     * 请求资源不可接受，一般用于hibernate-validation返回异常时返回此响应码
     */
    NOT_ACCEPTABLE_406("406", "resource not acceptable!"),

    /**
     * 服务器内部异常
     */
    INTERNAL_SERVER_ERROR_500("500", "internal server error!"),

    /**
     * 上游服务器无响应！
     */
    BAD_GATEWAY_502("502", "Bad gateway!"),

    /**
     * 上游服务器返回异常
     */
    SERVICE_UNAVAILABLE_503("503", "service unavailable!"),

    /**
     * 网关超时
     */
    GATEWAY_TIMEOUT_504("504", "gateway timeout!");

    @Getter
    private String code;
    @Getter
    private String desc;

    ResponseCodeEnum(String code, String desc ) {
        this.code = code;
        this.desc = desc;
    }
}
