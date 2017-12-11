package com.gtercn.carhome.web.api.service.homeCarousel;

import java.util.List;

import com.gtercn.carhome.web.api.entity.HomeCarousel;

public interface HomeCarouselService {

	/**
	 * 查询
	 * 
	 * @return
	 */
	public List<HomeCarousel> selectByCityCode(String cityCode);
}
