package com.example.tang.androidframe.http;

/**
 * @author TangAnna
 * @description: Api异常需要配合后台开发人员处理
 * @date :${DATA} 17:36
 */
public class ApiException extends RuntimeException {
    public int errorCode;
    public String errorMsg;

    public ApiException(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
