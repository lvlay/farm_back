package com.taoroot.service;

import com.taoroot.common.ServerResponse;
import com.taoroot.pojo.Greenhouse;

/**
 * @author: taoroot
 * @date: 2018/2/5
 * @description:
 */

public interface IGreenhouseService {
    ServerResponse add(Greenhouse greenhouse, int userId);

    ServerResponse getList(int pageNum, int pageSize, String title, String orderBy, int userId);

    ServerResponse getListNoPage(int userId);
}
