package com.taoroot.common;

/**
 * Created by taoroot
 * <p>
 * 登录方式
 */
public enum StatusTypeCode {

    ONLINE(0, "ONLINE"),
    OFFLINE(1, "OFFLINE"),
    DISABLE(2, "DISABLE");

    private final int code;
    private final String desc;


    StatusTypeCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
