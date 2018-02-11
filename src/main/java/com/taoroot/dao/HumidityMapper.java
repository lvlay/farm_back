package com.taoroot.dao;

import com.taoroot.pojo.Humidity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HumidityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Humidity record);

    int insertSelective(Humidity record);

    Humidity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Humidity record);

    int updateByPrimaryKey(Humidity record);

    List<Humidity> selectListByDevice(@Param("deviceId")int deviceId, @Param("size")int size);
}