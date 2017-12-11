package com.gtercn.carhome.web.api.service.rescue;

import java.util.List;

import com.gtercn.carhome.web.api.entity.Rescue;
import com.gtercn.carhome.web.api.entity.RescueDetail;
import com.gtercn.carhome.web.api.entity.RescueType;
import com.gtercn.carhome.web.api.entity.ServiceDetail;
import com.gtercn.carhome.web.api.entity.ServiceType;


public interface IRescueService {
	
	// 获取类型下拉菜单
	public abstract List<String> getTypeList();
	
	// 获取地区下拉菜单
	public abstract List<String> getDistrictList();
	
	// 获取救援信息列表
	public abstract List<Rescue> getInfoList(String longtitude, String latitude, String userId);
	
	// 获取救援检索信息列表
	public abstract List<Rescue> getInfoSearchList(String longtitude, String latitude, String userId, String keyword);
		
	// 获取单个救援信息
	public abstract Rescue getOneRescueInfo(String shopId, String userId);
	
	// 获取洗车修车等信息列表
	public abstract List<Rescue> getServiceList(String longtitude, String latitude, String state, String userId);
	
	// 获取洗车修车等检索信息列表
	public abstract List<Rescue> getServiceSearchList(String longtitude, String latitude, String state, String userId, String keyword);
	
	// 获取单个商家洗车修车等信息
	public abstract Rescue getServiceDifType(String longtitude, String latitude, String shopId, String serviceType, String userId);
	
	
	// 获取四类服务信息列表
	public abstract List<ServiceType> getServiceTypeList(String cityCode, int beginNum, int overNum);
	
	// 获取四类服务信息详情
	public abstract ServiceDetail getServiceTypeDetail(String shopId, String userId);
	
	// 获取救援信息列表
	public abstract List<RescueType> getRescueList(String cityCode, int beginNum, int overNum);
	
	// 获取救援服务信息详情
	public abstract RescueDetail getRescueTypeDetail(String rescueId, String userId);
}
