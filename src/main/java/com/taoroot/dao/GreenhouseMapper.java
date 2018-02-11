package com.taoroot.dao;

import com.taoroot.pojo.Greenhouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GreenhouseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Greenhouse record);

    int insertSelective(Greenhouse record);

    Greenhouse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Greenhouse record);

    int updateByPrimaryKey(Greenhouse record);

    List<Greenhouse> selectListByUser(@Param("title")String title, @Param("userId")int userId);
}