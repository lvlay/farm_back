package com.taoroot.common;

/**
 * Created by taoroot
 * <p>
 * HTTP 请求类型
 */
public enum RequestMethodCode {

    GET(0, "GET"),
    POST(1, "POST"),
    PUT(2, "PUT"),
    DELETE(3, "DELETE");

    private final int code;
    private final String desc;


    RequestMethodCode(int code, String desc) {
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
