package com.gtercn.carhome.web.api.service.city;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.dao.CityMapper;
import com.gtercn.carhome.web.api.entity.City;

@Service(value = "cityService")
public class CityServiceImpl implements CityService {
	@Autowired
	private CityMapper dao;
	@Override
	public List<City> getCityList() {
		
		return dao.getCityList();
	}
}
