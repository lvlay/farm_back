package com.taoroot.common;

/**
 * Created by taoroot
 * <p>
 * 资源类型
 */
public enum ResourceTypeCode {

    FUNCTION(0, "FUNCTION"),
    MENU(1, "MENU"),
    ALL(2, "ALL");

    private final int code;
    private final String desc;

    ResourceTypeCode(int code, String desc) {
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
