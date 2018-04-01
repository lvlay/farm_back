package com.taoroot.common;

/**
 * Created by taoroot
 * <p>
 * 登录方式
 */
public enum StatusTypeCode {

    ENABLE(0, "ENABLE"),
    DISABLE(1, "DISABLE");

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
