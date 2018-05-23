package com.springboot.model.base;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lvgang on 2018/4/30 2:43
 */
@Data
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 5630557310745846627L;
    private boolean success;
    private T result;
    private String errorCode;
    private String errorMsg;

    public Response(){}
    public Response(T result) {
        this.success = true;
        this.result = result;
    }

    public Response(boolean flag, T result) {
        if (flag) {
            this.success = true;
            this.result = result;
        } else {
            this.success = false;
            this.errorCode = (String)result;
        }

    }

    public Response(String errorCode) {
        this.success = false;
        this.errorCode = errorCode;
    }

    public Response(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Response response = (Response)o;
            if (this.success != response.success) {
                return false;
            } else if (!this.errorCode.equals(response.errorCode)) {
                return false;
            } else {
                return this.result.equals(response.result);
            }
        } else {
            return false;
        }
    }
    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.success = true;
        this.result = result;
    }
    public int hashCode() {
        int result1 = this.success ? 1 : 0;
        result1 = 31 * result1 + this.result.hashCode();
        result1 = 31 * result1 + this.errorCode.hashCode();
        return result1;
    }

    public String toString() {
        return "Response{" +
                "success=" + success +
                ", result=" + result +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
