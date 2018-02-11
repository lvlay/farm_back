package com.taoroot.service;

import com.taoroot.common.ServerResponse;
import com.taoroot.pojo.Device;

public interface IDeviceService {

    ServerResponse getDeviceList(int pageNum, int pageSize, String title, String orderBy, int userId);

    ServerResponse getDeviceListNoPage(int userId);

    ServerResponse register(String id, int userId);

    boolean hasDeviceByUser(int userId, int did);

    boolean hasDeviceByGreenhouse(int greenhouseId, int deviceId);

    ServerResponse update(Device device, int userId);

    ServerResponse bindByGreenhouse(int[] dids, int hid, int userId);

}
