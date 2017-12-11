package com.gtercn.carhome.web.api.service.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.CarHomeApiProperties;
import com.gtercn.carhome.web.api.dao.HomeSearchMapper;
import com.gtercn.carhome.web.api.entity.Rescue;

@Service(value="homeSearchService")
public class HomeSearchServiceImpl implements HomeSearchService {
	@Autowired
	private HomeSearchMapper dao;
	
	@Override
	public List<Rescue> getSearchData(Map<String, Object> map) {
		
		List<Rescue> list = dao.getSearchData(map);
		return sortRescue(list);
				
	}

	/**
	 * 对紧急返回的list进行整合
	 * @param list 检索的list
	 * @param longtitude 经度
	 * @param latitude 纬度
	 * @return
	 */
	private List<Rescue> sortRescue(List<Rescue> list) {
		
		String[] telNum = null;
		String[] displayPicUrl = null;
		List<String> telNumList = new ArrayList<String>();
		List<String> displayPicUrlList = new ArrayList<String>();
		List<String> serviceList = new ArrayList<String>();
		
		for (int i = 0; i < list.size(); i++) {
			
			// 电话
			telNum = list.get(i).getTelNumberList().split(",");
			for (int j = 0; j < telNum.length; j++) {
				telNumList.add(telNum[j]);
			}
			
			// 包含救援服务
			if (!StringUtils.equals("0", list.get(i).getRescueService())) {
				serviceList.add("6");
			}
			// 包含轮胎服务
			if (!StringUtils.equals("0", list.get(i).getTyreService())) {
				serviceList.add("7");
			}
			// 包含保养服务
			if (!StringUtils.equals("0", list.get(i).getMaintainService())) {
				serviceList.add("8");
			}
			// 包含洗车服务
			if (!StringUtils.equals("0", list.get(i).getCleanService())) {
				serviceList.add("9");
			}
			// 包含修车服务
			if (!StringUtils.equals("0", list.get(i).getRepairService())) {
				serviceList.add("10");
			}
			
			// 公司详情
			if (StringUtils.isNotBlank(list.get(i).getDisplayPicUrlList())) {
				displayPicUrl = list.get(i).getDisplayPicUrlList().split(",");
				for (int j = 0; j < displayPicUrl.length; j++) {
					displayPicUrlList.add(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ displayPicUrl[j]);
				}
			}
			list.get(i).setTelNumList(telNumList);
			list.get(i).setServiceList(serviceList);
			list.get(i).setDisplayPicList(displayPicUrlList);
			list.get(i).setHeadPortraitUrl(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ list.get(i).getHeadPortraitUrl());
			list.get(i).setShopPicUrl(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ list.get(i).getShopPicUrl());
			
			// 重新初始化
			telNumList = new ArrayList<String>();
			serviceList = new ArrayList<String>();
			displayPicUrlList = new ArrayList<String>();;
			telNum = null;
		}
		
		return list;
	}
}
