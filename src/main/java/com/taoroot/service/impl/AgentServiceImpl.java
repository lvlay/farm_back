package com.taoroot.service.impl;

import com.taoroot.dao.DeviceMapper;
import com.taoroot.dao.HumidityMapper;
import com.taoroot.dao.TemperatureMapper;
import com.taoroot.pojo.Device;
import com.taoroot.pojo.Humidity;
import com.taoroot.pojo.Temperature;
import com.taoroot.redis.entiy.FarmAgentMessage;
import com.taoroot.service.IAgentService;
import com.taoroot.util.ConfigUtil;
import com.taoroot.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: taoroot
 * @date: 2018/1/29
 * @description:
 */
@Service("IAgentService")
public class AgentServiceImpl implements IAgentService {
    private static String TOKEN_SECURITY = ConfigUtil.get("deviceSecurity");

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private TemperatureMapper temperatureMapper;
    @Autowired
    private HumidityMapper humidityMapper;

    @Override
    public FarmAgentMessage registerHandler(FarmAgentMessage msg) {
        int result;
        String token = getToken(msg.getMacAddress());
        Device device = deviceMapper.selectByDid(msg.getMacAddress());
        if (device == null) {
            device = new Device();
            device.setDid(msg.getMacAddress());
            device.setModel(msg.getModel());
            device.setToken(token);
            result = deviceMapper.insert(device);
            if(result > 0) {
                Device _device = deviceMapper.selectByDid(msg.getMacAddress());
                msg.setdId(_device.getId());
            }

        } else {
            device.setToken(token);
            msg.setdId(device.getId());
            result = deviceMapper.updateByPrimaryKeySelective(device);
        }
        if (result > 0) {
            msg.setToken(token);
        }
        return msg;
    }

    @Override
    public FarmAgentMessage temperatureHandler(FarmAgentMessage farmAgentMessage) {
        Temperature temperature = new Temperature();
        temperature.setDeviceId(farmAgentMessage.getdId());
        temperature.setData(farmAgentMessage.getTemperature());
        temperatureMapper.insert(temperature);
        return null;
    }

    @Override
    public FarmAgentMessage humidityHandler(FarmAgentMessage farmAgentMessage) {
        Humidity humidity = new Humidity();
        humidity.setDeviceId(farmAgentMessage.getdId());
        humidity.setData(farmAgentMessage.getHumidity());
        humidity.setDeviceId(farmAgentMessage.getdId());
        // 大于0的id才是有效的id
        if (farmAgentMessage.getdId() > 0) {
            humidityMapper.insert(humidity);
        }
        return null;
    }

    /**
     * 获取token
     *
     * @param id
     * @return
     */
    private String getToken(String id) {
        return MD5Util.md5(id + TOKEN_SECURITY);
    }
}
