package com.taoroot.service.impl;

import com.taoroot.common.ResponseCode;
import com.taoroot.common.ServerResponse;
import com.taoroot.dao.HumidityMapper;
import com.taoroot.dao.TemperatureMapper;
import com.taoroot.service.IDataService;
import com.taoroot.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: taoroot
 * @date: 2018/2/4
 * @description:
 */
@Service("IDataService")
public class DataServiceImpl implements IDataService {

    @Autowired
    private IDeviceService iDeviceService;

    @Autowired
    private TemperatureMapper temperatureMapper;

    @Autowired
    private HumidityMapper humidityMapper;


    @Override
    public ServerResponse list(int id, String type, int size, int userId) {

        // 用户是否拥有设备
        if (!iDeviceService.hasDeviceByUser(userId, id)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_ADMIN.getCode(), null);
        }
        // 返回数据
        switch (type) {
            case "temperature":
                return ServerResponse.createBySuccess(temperatureMapper.selectListByDevice(id, size));
            case "humidity":
                return ServerResponse.createBySuccess(humidityMapper.selectListByDevice(id, size));
            default:
                return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), null);
        }
    }
}
