package com.gtercn.carhome.web.api.entity;

import java.util.List;
import java.util.Map;

public class Rescue {
	
	// 主键
	private String id;
	
	// 救援服务
	private String rescueService;
	
	// 修车服务
	private String repairService;
	
	// 洗车服务
	private String cleanService;
	
	// 保养服务
	private String maintainService;
	
	// 轮胎服务
	private String tyreService;
		
	// 救援类型
	private String typeValue;
	
	// 救援公司图片
	private String shopPicUrl;
	
	// 救援公司名字
	private String shopName;
	
	// 救援公司ID
	private String shopId;
	
	// 救援公司评分
	private String shopScore;
	
	// 救援公司救援经验
	private String experience;
	
	// 救援公司所在地经度
	private String longitude;
	
	// 救援公司所在地纬度
	private String latitude;
	
	// 距离
	private Double distance;
	
	// 省
	private String province;
	
	// 市
	private String city;
	
	// 区
	private String district;
	
	// 详细地址
	private String detailAddress;
	
	// 救援公司电话列表
	private String telNumberList;
	
	// 救援公司详情头像
	private String headPortraitUrl;
	
	// 公司详情图片URL
	private String displayPicUrlList;
	
	// 救援公司介绍
	private String shopDescription;
	
	// 救援公司产品介绍
	private String productDescription;

	// 删除标记
    private Integer deleteFlag;

    // 更新时间
    private String updateTime;

    // 创建时间
    private String insertTime;
    
    // 按类型
    private List<String> typeList;
    
    // 按距离
    private List<String> distanceList;
    
    // 按区域
    private List<String> districtList;
    
    // 电话集
    private List<String> telNumList;
    
    // 公司详情图片URL
    private List<String> displayPicList;
    
    // 汽车图标URL
    private String serviceBrandsUrl;
    
    // 汽车图标URLlist
    private List<String> serviceBrandsUrlList;
    
    // 服务类型List
 	private Map<String, String> serviceTypeList;
 	
 	// 紧急救援类型List
  	private List<String> typeValueList;
  	
  	// 是否收藏
  	private int isFavored;
  	
  	// 包含的服务
  	private List<String> serviceList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
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

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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

	public String getHeadPortraitUrl() {
		return headPortraitUrl;
	}

	public void setHeadPortraitUrl(String headPortraitUrl) {
		this.headPortraitUrl = headPortraitUrl;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public List<String> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}

	public List<String> getTelNumList() {
		return telNumList;
	}

	public void setTelNumList(List<String> telNumList) {
		this.telNumList = telNumList;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getServiceBrandsUrl() {
		return serviceBrandsUrl;
	}

	public void setServiceBrandsUrl(String serviceBrandsUrl) {
		this.serviceBrandsUrl = serviceBrandsUrl;
	}

	public List<String> getServiceBrandsUrlList() {
		return serviceBrandsUrlList;
	}

	public void setServiceBrandsUrlList(List<String> serviceBrandsUrlList) {
		this.serviceBrandsUrlList = serviceBrandsUrlList;
	}

	public List<String> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<String> districtList) {
		this.districtList = districtList;
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

	public String getShopScore() {
		return shopScore;
	}

	public void setShopScore(String shopScore) {
		this.shopScore = shopScore;
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

	public List<String> getDisplayPicList() {
		return displayPicList;
	}

	public void setDisplayPicList(List<String> displayPicList) {
		this.displayPicList = displayPicList;
	}

	public Map<String, String> getServiceTypeList() {
		return serviceTypeList;
	}

	public void setServiceTypeList(Map<String, String> serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}

	public String getRescueService() {
		return rescueService;
	}

	public void setRescueService(String rescueService) {
		this.rescueService = rescueService;
	}

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

	public List<String> getTypeValueList() {
		return typeValueList;
	}

	public void setTypeValueList(List<String> typeValueList) {
		this.typeValueList = typeValueList;
	}

	public List<String> getDistanceList() {
		return distanceList;
	}

	public void setDistanceList(List<String> distanceList) {
		this.distanceList = distanceList;
	}

	public int getIsFavored() {
		return isFavored;
	}

	public void setIsFavored(int isFavored) {
		this.isFavored = isFavored;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public List<String> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<String> serviceList) {
		this.serviceList = serviceList;
	}
}