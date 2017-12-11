package com.gtercn.carhome.web.api.dao;

import java.util.List;

import com.gtercn.carhome.web.api.entity.HomeCarousel;

public interface HomeCarouselMapper {
    
    /**
     * 根据城市获得首页轮播
     * @param cityCode 城市编号
     * @return
     */
    List<HomeCarousel> selectByCityCode(String cityCode);
    
}