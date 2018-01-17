package com.taoroot.common;

/**
 * Created by taoroot
 *
 * 登录方式
 */
public enum LoginTypeCode {

    WEB(0,"WEB"),
    PHONE(1,"PHONE");

    private final int code;
    private final String desc;


    LoginTypeCode(int code, String desc){
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
