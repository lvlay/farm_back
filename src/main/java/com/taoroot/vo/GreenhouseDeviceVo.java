package com.taoroot.vo;

import com.taoroot.pojo.Device;
import com.taoroot.pojo.Greenhouse;

import java.util.List;

/**
 * @author: taoroot
 * @date: 2018/2/10
 * @description:
 */
public class GreenhouseDeviceVo extends Greenhouse{
    private List<Device> devices;

    public GreenhouseDeviceVo(Greenhouse g, List<Device> devices) {
        super(g.getId(), g.getName(), g.getUserId());
        this.devices = devices;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public GreenhouseDeviceVo setDevices(List<Device> devices) {
        this.devices = devices;
        return this;
    }
}
