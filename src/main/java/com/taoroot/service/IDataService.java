package com.taoroot.service;

import com.taoroot.common.ServerResponse;

/**
 * @author: taoroot
 * @date: 2018/2/4
 * @description: 数据服务
 */
public interface IDataService {

     ServerResponse list(int id, String type, int userId);
}
