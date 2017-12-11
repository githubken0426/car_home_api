package com.gtercn.carhome.web.api.dao;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.web.api.entity.Promotion;

public interface PromotionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Promotion record);

    int insertSelective(Promotion record);
    /**
     * 详情
     * @param id
     * @return
     * 2017-3-14 上午09:55:24
     */
    Promotion selectPromotionDetail(Map<String,Object> map);

    int updateByPrimaryKeySelective(Promotion record);

    int updateByPrimaryKey(Promotion record);
    
    /**
     * 查询所有促销信息
     * @return
     * 2016-12-23 下午03:13:01
     */
    public List<Promotion> selectAllData(Map<String,Object> map);
}