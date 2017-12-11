package com.gtercn.carhome.web.api.service.city;

import java.util.List;

import com.gtercn.carhome.web.api.entity.City;

public interface CityService {
	
	/**
	 * 获取城市列表数据
	 * @return
	 * @throws Exception
	 */
	public List<City> getCityList();
}
