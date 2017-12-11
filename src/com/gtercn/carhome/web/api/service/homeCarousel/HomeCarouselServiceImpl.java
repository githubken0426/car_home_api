package com.gtercn.carhome.web.api.service.homeCarousel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.dao.HomeCarouselMapper;
import com.gtercn.carhome.web.api.entity.HomeCarousel;


@Service(value = "homeCarouselService")
public class HomeCarouselServiceImpl implements HomeCarouselService {
	@Autowired
	private HomeCarouselMapper dao;

	@Override
	public List<HomeCarousel> selectByCityCode(String cityCode) {
		return dao.selectByCityCode(cityCode	);
	}
}
