package com.taoroot.common;

/**
 * Created by taoroot
 *
 * 自定义的响应码
 */
public enum ResponseCode {

    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(3,"NEED_LOGIN"),
    NEED_PERMISSION(401, "NEED_PERMISSION"),
    ILLEGAL_ARGUMENT(400,"ILLEGAL_ARGUMENT"),
    ERROR_BACKEND(401, "ERROR_BACKEND");

    private final int code;
    private final String desc;


    ResponseCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

}
