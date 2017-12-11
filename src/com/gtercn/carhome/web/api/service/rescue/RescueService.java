package com.gtercn.carhome.web.api.service.rescue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.CarHomeApiProperties;
import com.gtercn.carhome.web.api.dao.DictionaryMapper;
import com.gtercn.carhome.web.api.dao.RescueMapper;
import com.gtercn.carhome.web.api.entity.Dictionary;
import com.gtercn.carhome.web.api.entity.Rescue;
import com.gtercn.carhome.web.api.entity.RescueDetail;
import com.gtercn.carhome.web.api.entity.RescueType;
import com.gtercn.carhome.web.api.entity.ServiceDetail;
import com.gtercn.carhome.web.api.entity.ServiceType;

@Service
public class RescueService implements IRescueService {
	@Autowired
	private RescueMapper rescueDao;
	
	@Autowired
	private DictionaryMapper dicEntityDao;

	/**
	 * 获取类型下拉菜单
	 */
	@Override
	public List<String> getTypeList() {
		
		List<Dictionary> list = this.dicEntityDao.selectByTypeFlag("2100");
		List<String> list1 = new ArrayList<String>();
		for (int i =0; i < list.size(); i++) {
			list1.add(list.get(i).getDictionaryName());
		}
		return	list1;
	}
	
	/**
	 * 获取地区下拉菜单
	 */
	@Override
	public List<String> getDistrictList() {
		
		List<Dictionary> list = this.dicEntityDao.selectByTypeFlag("500");
		List<String> list1 = new ArrayList<String>();
		for (int i =0; i < list.size(); i++) {
			list1.add(list.get(i).getDictionaryName());
		}
		return	list1;
	}

	/**
	 * 获取救援信息列表
	 */
	@Override
	public List<Rescue> getInfoList(String longtitude, String latitude, String userId) {
		
		List<Rescue> list = new ArrayList<Rescue>();
		
		// 检索参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		
		// 获取救援信息列表
		list = this.rescueDao.getInfoList(map);
		
		// 对返回的list进行整合
		list = sortRescue(list, longtitude, latitude);
		
		return list;
	}
	
	/**
	 * 获取救援信息列表
	 */
	@Override
	public List<Rescue> getInfoSearchList(String longtitude, String latitude, String userId, String keyword) {
		
		List<Rescue> list = new ArrayList<Rescue>();
		
		// 检索参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("keyword", keyword);
		
		// 获取救援信息列表
		list = this.rescueDao.getInfoSearchList(map);
		
		// 对返回的list进行整合
		list = sortRescue(list, longtitude, latitude);
		
		return list;
	}
	
	/**
	 * 获取单个救援信息
	 */
	@Override
	public Rescue getOneRescueInfo(String shopId, String userId) {
		
		// 检索参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		map.put("userId", userId);
		
		Rescue rescue = this.rescueDao.getOneRescueInfo(map);
		
		return rescue;
	}
	
	/**
	 * 获取洗车修车等信息列表
	 */
	@Override
	public List<Rescue> getServiceList(String longtitude, String latitude, String state, String userId) {
		
		List<Rescue> list = new ArrayList<Rescue>();
		
		// 检索参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 修车
		map.put("serviceType", state);
		map.put("userId", userId);
		
		// 获取救援信息列表
		list = this.rescueDao.getServiceList(map);
		
		// 对返回的list进行整合
		list = sort(list, longtitude, latitude);
				
		return list;
	}
	
	/**
	 * 获取洗车修车等检索信息列表
	 */
	@Override
	public List<Rescue> getServiceSearchList(String longtitude, String latitude, String state, String userId, String keyword) {
		
		List<Rescue> list = new ArrayList<Rescue>();
		
		// 检索参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 修车
		map.put("serviceType", state);
		map.put("userId", userId);
		map.put("keyword", keyword);
		
		// 获取救援信息列表
		list = this.rescueDao.getServiceSearchList(map);
		
		// 对返回的list进行整合
		list = sort(list, longtitude, latitude);
				
		return list;
	}
	
	/**
	 * 获取单个商家洗车修车等信息
	 */
	@Override
	public Rescue getServiceDifType(String longtitude, String latitude, String shopId, String serviceType, String userId) {
		
		// 检索参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serviceType", serviceType);
		map.put("shopId", shopId);
		map.put("userId", userId);
		
		// 获取救援信息列表
		Rescue rescue = this.rescueDao.getServiceDifType(map);
		
		// 客户经度
		Double long1 = Double.parseDouble(longtitude);
		// 客户纬度
		Double lat1 = Double.parseDouble(latitude);
		// 救援公司经度
		Double long2 = new Double(0);
		// 救援公司纬度
		Double lat2 = new Double(0);
		// 距离
		Double distance = new Double(0);
		
		distance = Distance(long1, lat1, long2, lat2);
		rescue.setDistance(distance);
				
		return rescue;
	}
	
	/**
	 * 对紧急返回的list进行整合
	 * @param list 检索的list
	 * @param longtitude 经度
	 * @param latitude 纬度
	 * @return
	 */
	private List<Rescue> sortRescue(List<Rescue> list, String longtitude, String latitude) {
		
		// 客户经度
		Double long1 = Double.parseDouble(longtitude);
		// 客户纬度
		Double lat1 = Double.parseDouble(latitude);
		// 救援公司经度
		Double long2 = new Double(0);
		// 救援公司纬度
		Double lat2 = new Double(0);
		// 距离
		Double distance = new Double(0);
		
		Rescue rescue = new Rescue();
		String[] telNum = null;
		String[] typeValue = null;
		String[] displayPicUrl = null;
		List<String> telNumList = new ArrayList<String>();
		List<String> typeValueList = new ArrayList<String>();
		List<String> displayPicUrlList = new ArrayList<String>();
		
		for (int i = 0; i < list.size(); i++) {
			
			long2 = Double.parseDouble(list.get(i).getLongitude());
			lat2 = Double.parseDouble(list.get(i).getLatitude());
			distance = Distance(long1, lat1, long2, lat2);
			list.get(i).setDistance(distance);
			// 电话
			telNum = list.get(i).getTelNumberList().split(",");
			for (int j = 0; j < telNum.length; j++) {
				telNumList.add(telNum[j]);
			}
			// 救援种类
			typeValue = list.get(i).getTypeValue().split(",");
			for (int j = 0; j < typeValue.length; j++) {
				Map<String, Object> mapDic = new HashMap<String, Object>();
				mapDic.put("dictionaryType", "2100");
				mapDic.put("dictionaryValue", typeValue[j]);
				Dictionary dictionary = this.dicEntityDao.selectByTypeFlagAndValue(mapDic);
				typeValueList.add(dictionary.getDictionaryName());
			}
			
			// 公司详情
			if (StringUtils.isNotBlank(list.get(i).getDisplayPicUrlList())) {
				displayPicUrl = list.get(i).getDisplayPicUrlList().split(",");
				for (int j = 0; j < displayPicUrl.length; j++) {
					displayPicUrlList.add(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ displayPicUrl[j]);
				}
			}
			list.get(i).setTelNumList(telNumList);
			list.get(i).setDisplayPicList(displayPicUrlList);
			list.get(i).setTypeValueList(typeValueList);
			list.get(i).setHeadPortraitUrl(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ list.get(i).getHeadPortraitUrl());
			list.get(i).setShopPicUrl(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ list.get(i).getShopPicUrl());
			
			// 重新初始化
			telNumList = new ArrayList<String>();
			displayPicUrlList = new ArrayList<String>();
			typeValueList = new ArrayList<String>();
			long2 = new Double(0);
			lat2 = new Double(0);
			telNum = null;
		}
		
		//按距离冒泡排序
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size() - 1 - i; j++) {
				if (list.get(j).getDistance() > list.get(j + 1).getDistance()) {
					rescue = list.get(j);
					list.set(j, list.get(j + 1));
					list.set(j + 1, rescue);
				}
			}
			rescue = new Rescue();
		}
		
		return list;
	}
	
	/**
	 * 对修车等返回的list进行整合
	 * @param list 检索的list
	 * @param longtitude 经度
	 * @param latitude 纬度
	 * @return
	 */
	private List<Rescue> sort(List<Rescue> list, String longtitude, String latitude) {
		
		// 客户经度
		Double long1 = Double.parseDouble(longtitude);
		// 客户纬度
		Double lat1 = Double.parseDouble(latitude);
		// 救援公司经度
		Double long2 = new Double(0);
		// 救援公司纬度
		Double lat2 = new Double(0);
		// 距离
		Double distance = new Double(0);
		
		Rescue rescue = new Rescue();
		String[] telNum = null;
		String[] displayPicUrl = null;
		String[] serviceBrand = null;
		List<String> telNumList = new ArrayList<String>();
		Map<String, String> serviceTypeList = new HashMap<String, String>();
		List<String> displayPicUrlList = new ArrayList<String>();
		List<String> serviceBrandList = new ArrayList<String>();
		
		for (int i = 0; i < list.size(); i++) {
			
			long2 = Double.parseDouble(list.get(i).getLongitude());
			lat2 = Double.parseDouble(list.get(i).getLatitude());
			distance = Distance(long1, lat1, long2, lat2);
			list.get(i).setDistance(distance);
			// 电话
			telNum = list.get(i).getTelNumberList().split(",");
			for (int j = 0; j < telNum.length; j++) {
				telNumList.add(telNum[j]);
			}
			// 服务类型list
			// 紧急救援
			if (list.get(i).getRescueService() != null) {
				List<Dictionary> listDictionary = this.dicEntityDao.selectByTypeFlag("2000");
				serviceTypeList.put("2100", listDictionary.get(0).getDictionaryName());
			} else {
				list.get(i).setRescueService("");
			}
			// 修车
			if (list.get(i).getRepairService() != null) {
				List<Dictionary> listDictionary = this.dicEntityDao.selectByTypeFlag("5100");
				serviceTypeList.put("5100", listDictionary.get(0).getDictionaryName());
			} else {
				list.get(i).setRepairService("");
			}
			// 洗车
			if (list.get(i).getCleanService() != null) {
				List<Dictionary> listDictionary = this.dicEntityDao.selectByTypeFlag("4100");
				serviceTypeList.put("4100", listDictionary.get(0).getDictionaryName());
			} else {
				list.get(i).setCleanService("");
			}
			// 保养
			if (list.get(i).getMaintainService() != null) {
				List<Dictionary> listDictionary = this.dicEntityDao.selectByTypeFlag("6100");
				serviceTypeList.put("6100", listDictionary.get(0).getDictionaryName());
			} else {
				list.get(i).setMaintainService("");
			}
			// 轮胎
			if (list.get(i).getTyreService() != null) {
				List<Dictionary> listDictionary = this.dicEntityDao.selectByTypeFlag("7100");
				serviceTypeList.put("7100", listDictionary.get(0).getDictionaryName());
			} else {
				list.get(i).setTyreService("");
			}
			
			// 公司详情
			if (StringUtils.isNotBlank(list.get(i).getDisplayPicUrlList())) {
				displayPicUrl = list.get(i).getDisplayPicUrlList().split(",");
				for (int j = 0; j < displayPicUrl.length; j++) {
					displayPicUrlList.add(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ displayPicUrl[j]);
				}
			}
			// 维修品牌
			if (list.get(i).getServiceBrandsUrl() != null) {
				serviceBrand = list.get(i).getServiceBrandsUrl().split(",");
				for (int j = 0; j < serviceBrand.length; j++) {
					serviceBrandList.add(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ serviceBrand[j]);
				}
				list.get(i).setServiceBrandsUrlList(serviceBrandList);
			}
			list.get(i).setTelNumList(telNumList);
			list.get(i).setServiceTypeList(serviceTypeList);
			list.get(i).setDisplayPicList(displayPicUrlList);
			
			list.get(i).setShopPicUrl(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ list.get(i).getShopPicUrl());
			
			// 重新初始化
			telNumList = new ArrayList<String>();
			serviceTypeList = new HashMap<String, String>();
			displayPicUrlList = new ArrayList<String>();
			serviceBrandList = new ArrayList<String>();
			long2 = new Double(0);
			lat2 = new Double(0);
			telNum = null;
		}
		
		//按距离冒泡排序
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size() - 1 - i; j++) {
				if (list.get(j).getDistance() > list.get(j + 1).getDistance()) {
					rescue = list.get(j);
					list.set(j, list.get(j + 1));
					list.set(j + 1, rescue);
				}
			}
			rescue = new Rescue();
		}
		
		return list;
	}
	
	/** 
	 * 计算地球上任意两点(经纬度)距离 
	 *  
	 * @param long1 
	 *            第一点经度 
	 * @param lat1 
	 *            第一点纬度 
	 * @param long2 
	 *            第二点经度 
	 * @param lat2 
	 *            第二点纬度 
	 * @return 返回距离 单位：米 
	 */  
	private static double Distance(double long1, double lat1, double long2,  
	        double lat2) {  
	    double a, b, R;  
	    R = 6378137; // 地球半径  
	    lat1 = lat1 * Math.PI / 180.0;  
	    lat2 = lat2 * Math.PI / 180.0;  
	    a = lat1 - lat2;  
	    b = (long1 - long2) * Math.PI / 180.0;  
	    double d;  
	    double sa2, sb2;  
	    sa2 = Math.sin(a / 2.0);  
	    sb2 = Math.sin(b / 2.0);
	    d = 2  
	            * R  
	            * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)  
	                    * Math.cos(lat2) * sb2 * sb2));
	    BigDecimal value = new BigDecimal(d / 1000); 
	    double f1 = value.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();  
	    return f1;
	}
	
	/**
	 * 获取四类服务类型列表
	 */
	@Override
	public List<ServiceType> getServiceTypeList(String cityCode, int beginNum, int overNum) {
		
		String[] telNum = null;
		List<ServiceType> list = new ArrayList<ServiceType>();
		List<String> telNumList = new ArrayList<String>();
		
		// 检索参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 四类服务
		map.put("cityCode", cityCode);
		map.put("beginNum", beginNum);
		map.put("overNum", overNum - beginNum + 1);
		
		// 获取救援信息列表
		list = this.rescueDao.getServiceTypeList(map);
		
		for (int i = 0; i < list.size(); i++) {
			
			// 电话
			telNum = list.get(i).getTelNumberList().split(",");
			for (int j = 0; j < telNum.length; j++) {
				telNumList.add(telNum[j]);
			}
			list.get(i).setTelNumList(telNumList);
			list.get(i).setShopPicUrl(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ list.get(i).getShopPicUrl());
			list.get(i).setCityCode(cityCode);
			
			// 重新初始化
			telNumList = new ArrayList<String>();
			telNum = null;
		}
				
		return list;
	}
	
	/**
	 * 获取单个救援信息
	 */
	@Override
	public ServiceDetail getServiceTypeDetail(String shopId, String userId) {
		
		// 检索参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		map.put("userId", userId);
		
		ServiceDetail serviceDetail = this.rescueDao.getOneServiceInfo(map);
		
		String[] displayPicUrl = null;
		String[] telNum = null;
		List<String> displayPicUrlList = new ArrayList<String>();
		List<String> telNumList = new ArrayList<String>();
		
		// 公司详情
		if (serviceDetail != null && StringUtils.isNotBlank(serviceDetail.getDisplayPicUrlList())) {
			displayPicUrl = serviceDetail.getDisplayPicUrlList().split(",");
			for (int j = 0; j < displayPicUrl.length; j++) {
				displayPicUrlList.add(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ displayPicUrl[j]);
			}
			serviceDetail.setDisplayPicList(displayPicUrlList);
		}
		
		// 电话
		telNum = serviceDetail.getTelNumberList().split(",");
		for (int j = 0; j < telNum.length; j++) {
			telNumList.add(telNum[j]);
		}
		serviceDetail.setTelNumList(telNumList);
		serviceDetail.setShopPicUrl(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ serviceDetail.getShopPicUrl());
		
		return serviceDetail;
	}
	
	/**
	 * 获取紧急救援类型列表
	 */
	@Override
	public List<RescueType> getRescueList(String cityCode, int beginNum, int overNum) {
		
		List<RescueType> list = new ArrayList<RescueType>();
		
		// 检索参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 四类服务
		map.put("cityCode", cityCode);
		map.put("beginNum", beginNum);
		map.put("overNum", overNum - beginNum + 1);
		
		// 获取按类型下拉菜单
		List<Dictionary> list1 = this.dicEntityDao.selectByTypeFlag("2100");
		
		JSONArray jsonArray = new JSONArray();
		JSONObject json = new JSONObject(); 
		for (int i = 0; i < list1.size(); i++) {
			json.put("key", list1.get(i).getDictionaryValue());
			json.put("value", list1.get(i).getDictionaryName());
			jsonArray.add(json);
			json = new JSONObject(); 
		}
		
		List<String> distanceList = new ArrayList<String>();
		distanceList.add("附近");
		distanceList.add("1");
		distanceList.add("3");
		distanceList.add("5");
		distanceList.add("10");
		
		// 获取救援信息列表
		list = this.rescueDao.getRescueTypeList(map);
		
		String[] telNum = null;
		List<String> telNumList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			
			// 电话
			if (StringUtils.isNotBlank(list.get(i).getTelNumberList())) {
				telNum = list.get(i).getTelNumberList().split(",");
				for (int j = 0; j < telNum.length; j++) {
					telNumList.add(telNum[j]);
				}
				list.get(i).setTelNumList(telNumList);
			}
			
			list.get(i).setCityCode(cityCode);
			list.get(i).setDistanceList(distanceList);
			list.get(i).setCategory(jsonArray.toString());
			list.get(i).setHeadPortraitUrl(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ list.get(i).getHeadPortraitUrl());
			telNumList = new ArrayList<String>();
			telNum = null;
		}
				
		return list;
	}
	
	/**
	 * 获取单个救援信息
	 */
	@Override
	public RescueDetail getRescueTypeDetail(String rescueId, String userId) {
		
		// 检索参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rescueId", rescueId);
		map.put("userId", userId);
		
		RescueDetail rescueDetail = this.rescueDao.getOneRescueTypeInfo(map);
		
		String[] telNum = null;
		String[] displayPicUrl = null;
		List<String> displayPicUrlList = new ArrayList<String>();
		List<String> telNumList = new ArrayList<String>();
		
		// 电话
		if (rescueDetail != null && StringUtils.isNotBlank(rescueDetail.getTelNumberList())) {
			telNum = rescueDetail.getTelNumberList().split(",");
			for (int j = 0; j < telNum.length; j++) {
				telNumList.add(telNum[j]);
			}
			rescueDetail.setTelNumList(telNumList);
		}
		
		// 公司详情
		if (rescueDetail != null && StringUtils.isNotBlank(rescueDetail.getDisplayPicUrlList())) {
			displayPicUrl = rescueDetail.getDisplayPicUrlList().split(",");
			for (int j = 0; j < displayPicUrl.length; j++) {
				displayPicUrlList.add(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ displayPicUrl[j]);
			}
			rescueDetail.setDisplayPicList(displayPicUrlList);
		}
		
		// 公司头像
		if (rescueDetail != null && StringUtils.isNotBlank(rescueDetail.getHeadPortraitUrl())) {
			rescueDetail.setHeadPortraitUrl(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ rescueDetail.getHeadPortraitUrl());
		}
		
		return rescueDetail;
	}
}
