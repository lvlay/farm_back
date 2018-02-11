package com.taoroot.dao;

import com.taoroot.pojo.GreenhouseDevice;

import java.util.List;

public interface GreenhouseDeviceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GreenhouseDevice record);

    int insertSelective(GreenhouseDevice record);

    GreenhouseDevice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GreenhouseDevice record);

    int updateByPrimaryKey(GreenhouseDevice record);

    List<GreenhouseDevice> selectByGreenhouse(int hid);

    int deleteByGreenhouse(int greenhouseId);
}