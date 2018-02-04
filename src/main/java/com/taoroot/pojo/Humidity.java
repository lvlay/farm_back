package com.taoroot.pojo;

import java.util.Date;

public class Humidity {
    private Long id;

    private Integer deviceId;

    private Float data;

    private Date createTime;

    public Humidity(Long id, Integer deviceId, Float data, Date createTime) {
        this.id = id;
        this.deviceId = deviceId;
        this.data = data;
        this.createTime = createTime;
    }

    public Humidity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Float getData() {
        return data;
    }

    public void setData(Float data) {
        this.data = data;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}