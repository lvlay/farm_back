package com.taoroot.common;

/**
 * Created by taoroot
 *
 * 登录方式
 */
public enum RoleTypeCode {

    USER(1,"USER"),
    ADMIN(0,"ADMIN");

    private final int code;
    private final String desc;


    RoleTypeCode(int code, String desc){
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
