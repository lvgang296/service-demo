package com.springboot.manager.utils;

import com.springboot.manager.enums.ResponseCodeEnum;
import lombok.Getter;

/**
 * Created by lvgang on 2018/4/30 3:02
 */
public class ServiceDemoException extends RuntimeException {

    private static final long serialVersionUID = 6869034995427712684L;
    @Getter
    private String code;

    public ServiceDemoException(String code ) {
        this.code = code;
    }

    public ServiceDemoException(String code, Throwable throwable ) {
        super(throwable);
        this.code = code;
    }

    public ServiceDemoException(String code, String message ) {
        super(message);
        this.code = code;
    }

    public ServiceDemoException(ResponseCodeEnum respCode ) {
        super(respCode.getDesc());
        this.code = respCode.getCode();
    }

    public ServiceDemoException(String code, String message, Throwable throwable ) {
        super(message, throwable);
        this.code = code;
    }

}
