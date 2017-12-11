package com.gtercn.carhome.web.api.controller.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtercn.carhome.web.api.CarHomeApiProperties;
import com.gtercn.carhome.web.api.entity.City;
import com.gtercn.carhome.web.api.entity.HomeCarousel;
import com.gtercn.carhome.web.api.enums.ErrorCode;
import com.gtercn.carhome.web.api.exception.ApiException;
import com.gtercn.carhome.web.api.form.HomeCarouselForm;
import com.gtercn.carhome.web.api.service.city.CityService;
import com.gtercn.carhome.web.api.service.homeCarousel.HomeCarouselService;
import com.gtercn.carhome.web.api.view.JsonList;

@Controller
@RequestMapping({ "/app/v1/open/home" })
public class HomeController {
	
	@Autowired
	private HomeCarouselService homeCarouselService;
	
	@Autowired
	private CityService cityService;

	@ResponseBody
	@RequestMapping(value = "/advert/list", method = RequestMethod.POST)
	public JsonList<Object> getAdvert(@RequestBody HomeCarouselForm form, HttpServletRequest request) throws ApiException {

		JsonList<Object> jsonList = new JsonList<Object>();
		List<Object> list = new ArrayList<Object>();
		
		String cityCode = form.getCityCode();
		
		List<HomeCarousel> adverList = homeCarouselService
				.selectByCityCode(cityCode);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (adverList.size() < 1) {
			throw new ApiException(ErrorCode.HOME_NULL_CODE);
		}
		for (int i = 0; i < adverList.size(); i++) {
			map.put("picture_url", CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip") + adverList.get(i).getPictureUrl());
			map.put("html_url", CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip") + adverList.get(i).getHtmlUrl());
			list.add(map);
			map = new HashMap<String, Object>();
		}
		jsonList.setResult(list);
		jsonList.setMessage("成功");
		return jsonList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/city/list", method = RequestMethod.POST)
	public JsonList<Object> getCity(HttpServletRequest request) throws ApiException {

		JsonList<Object> jsonList = new JsonList<Object>();
		List<Object> list = new ArrayList<Object>();
		List<City> cityList = cityService.getCityList();
		Map<String, Object> map = new HashMap<String, Object>();
		if (cityList.size() < 1) {
			throw new ApiException(ErrorCode.CITY_NULL_CODE);
		}
		for (int i = 0; i < cityList.size(); i++) {
			map.put("city_code", cityList.get(i).getCityCode());
			map.put("city_name", cityList.get(i).getCityName());
			map.put("city_phoneticize", cityList.get(i).getCityPhoneticize());
			list.add(map);
			map = new HashMap<String, Object>();
		}
		jsonList.setResult(list);
		jsonList.setMessage("城市检索成功");
		return jsonList;
	}
}
