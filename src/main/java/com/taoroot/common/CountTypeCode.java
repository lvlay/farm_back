package com.taoroot.common;

/**
 * Created by taoroot
 *
 * 统计时间
 */
public enum CountTypeCode {

    THIS_WEEK(0,"THIS_WEEK"),
    LAST_WEEK(1,"LAST_WEEK");

    private final int code;
    private final String desc;


    CountTypeCode(int code, String desc){
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
