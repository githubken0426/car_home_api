package com.gtercn.carhome.web.api.entity;

import java.util.List;

public class ServiceType {
	
	// id
	private String id;
	
	// 城市编号
	private String cityCode;
		
	// 修车服务
	private String repairService;
	
	// 洗车服务
	private String cleanService;
	
	// 保养服务
	private String maintainService;
	
	// 轮胎服务
	private String tyreService;
	
	// 救援公司图片
	private String shopPicUrl;
	
	// 救援公司名字
	private String shopName;
	
	// 救援公司评分
	private String score;
	
	// 详细地址
	private String detailAddress;
	
	// 救援公司电话列表
	private String telNumberList;
    
    // 电话集
    private List<String> telNumList;
  	
  	// 经度
  	private String longitude;
  	
  	// 纬度
  	private String latitude;

	public String getRepairService() {
		return repairService;
	}

	public void setRepairService(String repairService) {
		this.repairService = repairService;
	}

	public String getCleanService() {
		return cleanService;
	}

	public void setCleanService(String cleanService) {
		this.cleanService = cleanService;
	}

	public String getMaintainService() {
		return maintainService;
	}

	public void setMaintainService(String maintainService) {
		this.maintainService = maintainService;
	}

	public String getTyreService() {
		return tyreService;
	}

	public void setTyreService(String tyreService) {
		this.tyreService = tyreService;
	}

	public String getShopPicUrl() {
		return shopPicUrl;
	}

	public void setShopPicUrl(String shopPicUrl) {
		this.shopPicUrl = shopPicUrl;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getTelNumberList() {
		return telNumberList;
	}

	public void setTelNumberList(String telNumberList) {
		this.telNumberList = telNumberList;
	}

	public List<String> getTelNumList() {
		return telNumList;
	}

	public void setTelNumList(List<String> telNumList) {
		this.telNumList = telNumList;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
}