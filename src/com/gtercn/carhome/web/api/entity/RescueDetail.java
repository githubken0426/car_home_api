package com.gtercn.carhome.web.api.entity;

import java.util.List;


public class RescueDetail {
	
	// 主键
	private String id;
	
	// 救援公司电话列表
	private String telNumberList;
	
	// 电话集
	private List<String> telNumList;
	
	// 省
	private String province;
	
	// 市
	private String city;
	
	// 详细地址
	private String detailAddress;
	
	// 公司详情图片URL
	private String displayPicUrlList;
	
	// 公司详情图片URL
    private List<String> displayPicList;
	
    // 救援公司产品介绍
 	private String productDescription;
  	
  	// 是否收藏
  	private int isFavored;
  	
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
   	
   	// 店铺描述
   	private String shopDescription;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDisplayPicUrlList() {
		return displayPicUrlList;
	}

	public void setDisplayPicUrlList(String displayPicUrlList) {
		this.displayPicUrlList = displayPicUrlList;
	}

	public int getIsFavored() {
		return isFavored;
	}

	public void setIsFavored(int isFavored) {
		this.isFavored = isFavored;
	}

	public List<String> getDisplayPicList() {
		return displayPicList;
	}

	public void setDisplayPicList(List<String> displayPicList) {
		this.displayPicList = displayPicList;
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

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
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

	public String getShopDescription() {
		return shopDescription;
	}

	public void setShopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
	}
}