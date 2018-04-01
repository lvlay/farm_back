package com.taoroot.common;

/**
 * Created by taoroot
 * <p>
 * 资源设备类型
 */
public enum ResourceKindCode {

    WEB(0, "WEB"),
    APP(1, "APP");

    private final int code;
    private final String desc;


    ResourceKindCode(int code, String desc) {
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
