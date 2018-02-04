package com.taoroot.dao;

import com.taoroot.pojo.Humidity;

import java.util.List;

public interface HumidityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Humidity record);

    int insertSelective(Humidity record);

    Humidity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Humidity record);

    int updateByPrimaryKey(Humidity record);

    List<Humidity> selectListByDevice(int deviceId);
}