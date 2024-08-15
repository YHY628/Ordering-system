package com.yhy.exception;

// 缺失信息
public class InfoException extends RuntimeException {

    public InfoException() {
    }

    public InfoException(String message) {
        super(message);
    }
}
