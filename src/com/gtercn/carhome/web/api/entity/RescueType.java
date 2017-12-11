package com.gtercn.carhome.web.api.entity;

import java.util.List;



public class RescueType {
	
	// 救援id
	private String id;
	
	// 城市编号
	private String cityCode;
	
	// 救援类型
	private String typeValue;
	
	// 救援公司详情头像
	private String headPortraitUrl;
	
	// 救援公司名字
	private String shopName;
	
	// 救援公司评分
	private String shopScore;
  	
  	// 经度
  	private String longitude;
  	
  	// 纬度
  	private String latitude;
  	
  	// 救援类型下拉菜单
  	private String category;
  	
  	private List<String> distanceList;
  	
  	// 救援公司电话列表
 	private String telNumberList;
 	
 	// 电话集
 	private List<String> telNumList;
 	
 	// 市
 	private String city;
 	
 	// 详细地址
 	private String detailAddress;
  	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopScore() {
		return shopScore;
	}

	public void setShopScore(String shopScore) {
		this.shopScore = shopScore;
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

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public String getHeadPortraitUrl() {
		return headPortraitUrl;
	}

	public void setHeadPortraitUrl(String headPortraitUrl) {
		this.headPortraitUrl = headPortraitUrl;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getDistanceList() {
		return distanceList;
	}

	public void setDistanceList(List<String> distanceList) {
		this.distanceList = distanceList;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
}