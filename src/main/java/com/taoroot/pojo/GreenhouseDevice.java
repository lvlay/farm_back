package com.taoroot.pojo;

public class GreenhouseDevice {
    private int id;

    private int greenhouseId;

    private int deviceId;

    public GreenhouseDevice(int id, int greenhouseId, int deviceId) {
        this.id = id;
        this.greenhouseId = greenhouseId;
        this.deviceId = deviceId;
    }

    public GreenhouseDevice(int greenhouseId, int deviceId) {
        this.greenhouseId = greenhouseId;
        this.deviceId = deviceId;
    }

    public int getId() {
        return id;
    }

    public GreenhouseDevice setId(int id) {
        this.id = id;
        return this;
    }

    public int getGreenhouseId() {
        return greenhouseId;
    }

    public GreenhouseDevice setGreenhouseId(int greenhouseId) {
        this.greenhouseId = greenhouseId;
        return this;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public GreenhouseDevice setDeviceId(int deviceId) {
        this.deviceId = deviceId;
        return this;
    }
}