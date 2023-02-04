package com.cyyaw.config.exception;

import com.cyyaw.util.tools.WebErrCodeEnum;
import lombok.Data;

@Data
public class WebException extends RuntimeException {
    String message;
    Integer code;

    private WebException() {
    }

    private WebException(WebErrCodeEnum webErrCodeEnum) {
        this.message = webErrCodeEnum.getMsg();
        this.code = webErrCodeEnum.getCode();
    }

    private WebException(String msg, Integer code) {
        this.message = msg;
        this.code = code;
    }

    /**
     * 异常信息
     */
    public static void fail() {
        String msg = WebErrCodeEnum.WEB_ERR.getMsg();
        Integer code = WebErrCodeEnum.WEB_ERR.getCode();
        throw new WebException(msg, code);
    }

    /**
     * 异常信息
     *
     * @param msg 信息
     */
    public static void fail(String msg) {
        throw new WebException(msg, WebErrCodeEnum.WEB_ERR.getCode());
    }

    /**
     * 异常信息
     */
    public static void fail(WebErrCodeEnum webErrCodeEnum) {
        throw new WebException(webErrCodeEnum);
    }

    /**
     * 异常信息
     */
    public static void fail(WebErrCodeEnum webErrCodeEnum, String msg) {
        Integer code = webErrCodeEnum.getCode();
        throw new WebException(msg, code);
    }

}
