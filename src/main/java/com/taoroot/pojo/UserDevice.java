package com.taoroot.pojo;

public class UserDevice {
    private Integer id;

    private Integer userId;

    private Integer deviceId;

    public UserDevice(Integer id, Integer userId, Integer deviceId) {
        this.id = id;
        this.userId = userId;
        this.deviceId = deviceId;
    }

    public UserDevice() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
}