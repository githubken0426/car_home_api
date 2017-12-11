package com.gtercn.carhome.web.api.entity;

import java.util.List;


public class ServiceDetail extends ServiceType{
	
	// 主键
	private String id;
	
	// 省
	private String province;
	
	// 市
	private String city;
	
	// 公司详情图片URL
	private String displayPicUrlList;
	
	// 公司详情图片URL
    private List<String> displayPicList;
	
	// 救援公司介绍
	private String shopDescription;
	
	// 产品描述
	private String productDescription;
  	
  	// 是否收藏
  	private int isFavored;

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

	public String getShopDescription() {
		return shopDescription;
	}

	public void setShopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
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

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
}