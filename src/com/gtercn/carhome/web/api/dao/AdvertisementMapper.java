package com.gtercn.carhome.web.api.dao;

import java.util.List;

import com.gtercn.carhome.web.api.entity.Advertisement;

public interface AdvertisementMapper {
    int deleteByPrimaryKey(String advertisementId);

    int insert(Advertisement record);

    int insertSelective(Advertisement record);
    
    Advertisement selectByPrimaryKey(String advertisementId);

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKey(Advertisement record);
    //查询活动分类模块
    public List<Advertisement> getActivitySort();
    //查询顶部广告模块
    public List<Advertisement> getTopAdvertisement();
    //查询底部的优惠推荐广告模块
    public List<Advertisement> getBottomAdvertisement();
}