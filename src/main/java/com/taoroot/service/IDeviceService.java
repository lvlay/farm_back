package com.taoroot.service;

import com.taoroot.common.ServerResponse;

public interface IDeviceService {

    ServerResponse getDeviceList(int pageNum, int pageSize, String title, String orderBy, int userId);

    ServerResponse getDeviceListNoPage(int userId);

    boolean hasDeviceByUser(int userId, int did);
}
