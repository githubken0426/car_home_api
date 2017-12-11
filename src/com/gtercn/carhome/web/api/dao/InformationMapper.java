package com.gtercn.carhome.web.api.dao;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.web.api.entity.Information;

public interface InformationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectInformationDetail(Map<String,Object> map);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);
    
    /**
     * 查询所有数据
     * @return
     * 2016-12-27 上午09:02:29
     */
    public List<Information> selectAllData(Map<String,Object> map);
}