package com.taoroot.dao;

import com.taoroot.pojo.UserDevice;

public interface UserDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDevice record);

    int insertSelective(UserDevice record);

    UserDevice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDevice record);

    int updateByPrimaryKey(UserDevice record);
}