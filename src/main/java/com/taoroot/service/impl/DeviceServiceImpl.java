package com.taoroot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taoroot.common.ResponseCode;
import com.taoroot.common.RoleTypeCode;
import com.taoroot.common.ServerResponse;
import com.taoroot.dao.DeviceMapper;
import com.taoroot.dao.GreenhouseDeviceMapper;
import com.taoroot.dao.UserDeviceMapper;
import com.taoroot.dao.UserMapper;
import com.taoroot.pojo.Device;
import com.taoroot.pojo.GreenhouseDevice;
import com.taoroot.pojo.UserDevice;
import com.taoroot.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: taoroot
 * @date: 2018/1/29
 * @description:
 */
@Service("IDeviceService")
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDeviceMapper userDeviceMapper;
    @Autowired
    private GreenhouseDeviceMapper greenhouseDeviceMapper;

    @Override
    public ServerResponse getDeviceList(int pageNum, int pageSize, String title, String orderBy, int userId) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Device> deviceList;

        // 管理员的查找
        if (isAdmin(userId)) {
            deviceList = deviceMapper.selectList(title);
        } else {
            // 普通用户查找
            deviceList = deviceMapper.selectListByUser(title, userId);
        }

        PageInfo pageResult = new PageInfo(deviceList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse getDeviceListNoPage(int userId) {
        List<Device> deviceList;
        deviceList = deviceMapper.selectListByUser("", userId);
        return ServerResponse.createBySuccess(deviceList);
    }

    /**
     * 判断大棚是否已经设备绑定
     *
     * @return
     */
    @Override
    public boolean hasDeviceByGreenhouse(int greenhouseId, int deviceId) {
        int result = deviceMapper.hasDeviceByGreenhouse(greenhouseId, deviceId);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断用户是否有权限查看某个设备的信息
     *
     * @return
     */
    @Override
    public boolean hasDeviceByUser(int userId, int did) {
        int result = deviceMapper.hasDeviceByUser(userId, did);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ServerResponse update(Device device, int userId) {
        // 可以更新的设备信息
        if (!hasDeviceByUser(userId, device.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_ADMIN.getCode(), "权限不足");
        }
        // 安全操作
        Device selectDevice = deviceMapper.selectByPrimaryKey(device.getId());
        device.setToken(selectDevice.getToken());
        device.setDid(selectDevice.getDid());
        if (deviceMapper.updateByPrimaryKeySelective(device) > 0) {
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    @Override
    public ServerResponse bindByGreenhouse(int[] dids, int hid, int userId) {
        int result = 0;

        if (dids.length == 0) {
            result = 1;
        }
        greenhouseDeviceMapper.deleteByGreenhouse(hid);
        for (int did : dids) {
            // 跳过没有权限的
            if (!hasDeviceByUser(userId, did)) {
                continue;
            }

            if (!hasDeviceByGreenhouse(userId, did)) {
                result = greenhouseDeviceMapper.insert(new GreenhouseDevice(hid, did));
            }
        }

        if (result > 0) {
            return ServerResponse.createBySuccessMessage("绑定成功");
        }
        return ServerResponse.createByErrorMessage("绑定失败");
    }

    @Override
    public ServerResponse register(String id, int userId) {
        // 判断设备是否存在,
        Device device = deviceMapper.selectByDid(id);
        if (device == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "设备不存在");
        } else {
            // 判断重复注册
            if (hasDeviceByUser(userId, device.getId())) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "重复注册");
            } else {
                UserDevice userDevice = new UserDevice();
                userDevice.setDeviceId(device.getId());
                userDevice.setUserId(userId);
                int result = userDeviceMapper.insert(userDevice);
                if (result > 0) {
                    return ServerResponse.createBySuccess("注册成功", device);
                }
            }
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "设备失败");
    }


    /**
     * 判断用户身份
     *
     * @param userId
     * @return
     */
    private boolean isAdmin(int userId) {
        if (userMapper.selectRole(userId) == RoleTypeCode.ADMIN.getCode()) {
            return true;
        }
        return false;
    }


}
