package com.gtercn.carhome.web.api.entity;

import com.gtercn.carhome.web.api.utils.AppendServerURL;

public class HomeSearch {
	private String id;
	private String title;
	private String content;
	private String pictureList;
	private String nickName;
	private Integer supportNumber;//点赞数
	private Integer favorNumber;//收藏数
	private Integer glanceNumber;//浏览数
	private String publishTime;//发表时间
	private String address;
	private String shopScore;//评分
	private String experience;// 救援公司救援经验
	private Double distance;// 距离
	private String phone;
	private String shopId;
	//是否报名(1:报名，0：未报名)，当searchType=3时,是否收藏(1:已收藏，0未收藏)
	private Integer signFlag;
	//搜索类型1自驾游，2车友会，3达人圈，4资讯，5促销，6救援，7保养，8洗车，9修车
	private Integer searchType;
	private String insertTime;
	private String updateTime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getSupportNumber() {
		return supportNumber;
	}
	public void setSupportNumber(Integer supportNumber) {
		this.supportNumber = supportNumber;
	}
	public Integer getFavorNumber() {
		return favorNumber;
	}
	public void setFavorNumber(Integer favorNumber) {
		this.favorNumber = favorNumber;
	}
	public Integer getGlanceNumber() {
		return glanceNumber;
	}
	public void setGlanceNumber(Integer glanceNumber) {
		this.glanceNumber = glanceNumber;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getShopScore() {
		return shopScore;
	}
	public void setShopScore(String shopScore) {
		this.shopScore = shopScore;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public Integer getSignFlag() {
		return signFlag;
	}
	public void setSignFlag(Integer signFlag) {
		this.signFlag = signFlag;
	}
	public Integer getSearchType() {
		return searchType;
	}
	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getPictureList() {
		return pictureList;
	}
	//图片追加服务器地址
	public void setPictureList(String pictureList) {
		String url=AppendServerURL.appendFtpConfigPath(pictureList);
		this.pictureList = url;
	}
}
