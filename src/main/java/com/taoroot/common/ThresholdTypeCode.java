package com.taoroot.common;

/**
 * Created by taoroot
 * <p>
 * 阈值类型
 */
public enum ThresholdTypeCode {

    TEMPERATURE(0, "TEMPERATURE"),
    HUMIDITY(1, "HUMIDITY");

    private final int code;
    private final String desc;


    ThresholdTypeCode(int code, String desc) {
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
