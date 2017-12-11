package com.gtercn.carhome.web.api.form;


public class RescueForm {
	
    private String longtitude;//经度
    private String latitude;//纬度
    private String shopId;//商铺ID
    private String serviceType;//服务类型
    private String keyword;//搜索关键字
    
    // 城市编码
    private String cityCode;
    // 开始条数
    private String beginNumber;
    // 结束条数
    private String overNumber;
    
    // 救援id
    private String rescueId;

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude == null ? null : longtitude.trim();
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude == null ? null : latitude.trim();
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getBeginNumber() {
		return beginNumber;
	}

	public void setBeginNumber(String beginNumber) {
		this.beginNumber = beginNumber;
	}

	public String getOverNumber() {
		return overNumber;
	}

	public void setOverNumber(String overNumber) {
		this.overNumber = overNumber;
	}

	public String getRescueId() {
		return rescueId;
	}

	public void setRescueId(String rescueId) {
		this.rescueId = rescueId;
	}
}