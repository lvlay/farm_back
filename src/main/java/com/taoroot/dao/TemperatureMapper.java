package com.taoroot.dao;

import com.taoroot.pojo.Temperature;

import java.util.List;

public interface TemperatureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Temperature record);

    int insertSelective(Temperature record);

    Temperature selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Temperature record);

    int updateByPrimaryKey(Temperature record);

    List<Temperature> selectListByDevice(int deviceId);
}