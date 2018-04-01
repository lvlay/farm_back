package com.taoroot.service.sys;

import com.taoroot.common.RequestMethodCode;
import com.taoroot.common.ResourceTypeCode;
import com.taoroot.vo.ServerResponse;
import com.taoroot.pojo.sys.Resource;
import com.taoroot.service.IBaseService;

import java.util.List;

/**
 * @author: taoroot
 * @date: 2018/1/13
 * @description:
 */
public interface IResourceService extends IBaseService<Resource> {

    /**
     * @param userId 用户id
     * @param type   url 对应的功能
     * @param method http 请求类型
     * @return 获取到用户所有资源列表
     */
    List<Resource> getListByUserId(int userId, ResourceTypeCode type,  RequestMethodCode method);


    /**
     * @param userId 用户id
     * @param type   url 对应的功能
     * @param method http 请求类型
     * @return 获取到用户菜单列表列表
     */
    ServerResponse getMenuByUserId(int userId, ResourceTypeCode type, RequestMethodCode method);
}
