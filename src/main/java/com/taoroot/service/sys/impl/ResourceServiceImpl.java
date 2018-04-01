package com.taoroot.service.sys.impl;

import com.google.common.collect.Lists;
import com.taoroot.common.RequestMethodCode;
import com.taoroot.common.ResourceKindCode;
import com.taoroot.common.ResourceTypeCode;
import com.taoroot.vo.ServerResponse;
import com.taoroot.dao.sys.ResourceMapper;
import com.taoroot.pojo.sys.Resource;
import com.taoroot.service.BaseServiceImpl;
import com.taoroot.service.sys.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: taoroot
 * @date: 2018/1/13
 * @description:
 */
@Service("IResourceService")
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> getListByUserId(int userId, ResourceTypeCode type, RequestMethodCode method) {
        return getListByKind(userId, type, ResourceKindCode.WEB, method);
    }

    @Override
    public ServerResponse getMenuByUserId(int userId, ResourceTypeCode type, RequestMethodCode method) {
        List<Resource> resourceList = getListByKindAndParentId(userId, type, ResourceKindCode.WEB, method, 0);
        for (Resource resource : resourceList) {
            resource.setChildren(getListByKindAndParentId(userId, type, ResourceKindCode.WEB, method, resource.getId()));
        }
        return ServerResponse.createBySuccess(resourceList);
    }


    public List<Resource> getListByKind(int userId, ResourceTypeCode type, ResourceKindCode kind, RequestMethodCode method) {
        List<Resource> resourceList = Lists.newArrayList();
        switch (type) {
            case ALL:
//                resourceList.addAll(resourceMapper.getListByUserId(userId, kind.getDesc()));
                break;
            case FUNCTION:
                resourceList.addAll(resourceMapper.getListByUserId(userId, kind.getDesc(), ResourceTypeCode.FUNCTION.getCode(), method.getDesc()));
                break;
            case MENU:
                resourceList.addAll(resourceMapper.getListByUserId(userId, kind.getDesc(), ResourceTypeCode.MENU.getCode(), method.getDesc()));
                break;
        }
        return resourceList;
    }

    public List<Resource> getListByKindAndParentId(int userId, ResourceTypeCode type, ResourceKindCode kind, RequestMethodCode method, Integer parentId) {
        List<Resource> resourceList = Lists.newArrayList();
        switch (type) {
            case ALL:
//                resourceList.addAll(resourceMapper.getListByUserId(userId, kind.getDesc()));
                break;
            case FUNCTION:
                resourceList.addAll(resourceMapper.getListByUserIdParentId(userId, kind.getDesc(), ResourceTypeCode.FUNCTION.getCode(), method.getDesc(), parentId));
                break;
            case MENU:
                resourceList.addAll(resourceMapper.getListByUserIdParentId(userId, kind.getDesc(), ResourceTypeCode.MENU.getCode(), method.getDesc(), parentId));
                break;
        }
        return resourceList;
    }
}
