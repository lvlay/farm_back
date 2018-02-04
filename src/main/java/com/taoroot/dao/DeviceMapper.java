package com.taoroot.dao;

import com.taoroot.pojo.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    List<Device> selectList(String src);

    Device selectByDid(String did);

    List<Device> selectListByUser(@Param("str")String str, @Param("id")int id);

    int hasDeviceByUser(@Param("userId")int userId, @Param("deviceId")int deviceId);
}