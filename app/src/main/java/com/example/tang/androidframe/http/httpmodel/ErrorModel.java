package com.example.tang.androidframe.http.httpmodel;

/**
 * 错误类
 */
public class ErrorModel<T> {


    /**
     * code : 0
     * msg : Undefined index: sign
     * data : []
     */

    public int code;
    public String msg;
    public T data;
}
