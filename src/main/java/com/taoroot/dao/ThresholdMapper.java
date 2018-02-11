package com.taoroot.dao;

import com.taoroot.pojo.Threshold;

public interface ThresholdMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Threshold record);

    int insertSelective(Threshold record);

    Threshold selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Threshold record);

    int updateByPrimaryKey(Threshold record);
}