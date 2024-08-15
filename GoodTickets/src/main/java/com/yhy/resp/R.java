package com.yhy.resp;

import java.util.HashMap;
import java.util.Map;

public class R<T> {
    private int code;  //  2000  4000  6001 登录 6002 缺失信息
    private String message;
    private boolean success;
    private T data;

    private final static Map<String, String> EMPTY_DATA = new HashMap<>();

    public R() {
    }

    public R(int code, String message, boolean success, T data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public static R<?> FAIL(String message) {
        return new R<>(5000, message, false, EMPTY_DATA);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static <T> R<T> SUCCESS(String message, T data) {
        return new R<>(2000, message, true, data);
    }

    @Override
    public String toString() {
        return "R{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", success=" + success +
                ", data=" + data +
                '}';
    }

    public static R<?> LOGIN_FAIL(String message) {
        return new R<>(6001, message, false, EMPTY_DATA);
    }

    public static R<?> INFO_ERROR(String message) {
        return new R<>(6002, message, false, EMPTY_DATA);
    }
}
