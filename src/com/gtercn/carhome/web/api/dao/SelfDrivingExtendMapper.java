package com.gtercn.carhome.web.api.dao;

import java.util.Map;

import com.gtercn.carhome.web.api.entity.SelfDrivingExtend;

public interface SelfDrivingExtendMapper {
    int deleteByPrimaryKey(String id);

    int insert(SelfDrivingExtend record);

    int insertSelective(SelfDrivingExtend record);

    SelfDrivingExtend selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SelfDrivingExtend record);

    int updateByPrimaryKey(SelfDrivingExtend record);
    
    SelfDrivingExtend selectInfo(Map<String, Object> map);
}