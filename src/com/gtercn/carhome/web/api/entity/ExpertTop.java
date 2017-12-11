package com.gtercn.carhome.web.api.entity;

import com.gtercn.carhome.web.api.utils.AppendServerURL;
import com.gtercn.carhome.web.api.utils.FtpFilePath;

public class ExpertTop {
	private String id;
	private String userId;
	private String topTitle;
	private String expertName;
	private String expertDiscriptionShort;
	private String expertPortraitUrl;
	private String expertWechatNumber;
	private String expertExperience;
	private String expertTelNumber;
	private String motto;
	private String expertDisplayPicList;
	private String expertDiscriptionDetail;
	private Integer deleteFlag;
	private String insertTime;
	private String updateTime;
	private String cityCode;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}
	public String getTopTitle() {
		return topTitle;
	}
	public void setTopTitle(String topTitle) {
		this.topTitle = topTitle == null ? null : topTitle.trim();
	}
	public String getExpertName() {
		return expertName;
	}
	public void setExpertName(String expertName) {
		this.expertName = expertName == null ? null : expertName.trim();
	}
	public String getExpertDiscriptionShort() {
		return expertDiscriptionShort;
	}

	public void setExpertDiscriptionShort(String expertDiscriptionShort) {
		this.expertDiscriptionShort = expertDiscriptionShort == null ? null
				: expertDiscriptionShort.trim();
	}
	public String getExpertPortraitUrl() {
		return expertPortraitUrl;
	}
	public void setExpertPortraitUrl(String expertPortraitUrl) {
		String url = (expertPortraitUrl != null && expertPortraitUrl != "") ? 
				FtpFilePath.RESOURCES_IP + expertPortraitUrl
				: expertPortraitUrl;
		this.expertPortraitUrl = url;
	}
	public String getExpertWechatNumber() {
		return expertWechatNumber;
	}
	public void setExpertWechatNumber(String expertWechatNumber) {
		this.expertWechatNumber = expertWechatNumber == null ? null
				: expertWechatNumber.trim();
	}
	public String getExpertExperience() {
		return expertExperience;
	}
	public void setExpertExperience(String expertExperience) {
		this.expertExperience = expertExperience == null ? null
				: expertExperience.trim();
	}
	public String getExpertTelNumber() {
		return expertTelNumber;
	}
	public void setExpertTelNumber(String expertTelNumber) {
		this.expertTelNumber = expertTelNumber == null ? null : expertTelNumber
				.trim();
	}
	public String getMotto() {
		return motto;
	}
	public void setMotto(String motto) {
		this.motto = motto == null ? null : motto.trim();
	}
	public String getExpertDisplayPicList() {
		return expertDisplayPicList;
	}
	public void setExpertDisplayPicList(String expertDisplayPicList) {
		String url=AppendServerURL.appendFtpConfigPath(expertDisplayPicList);
		this.expertDisplayPicList = url;
	}
	public String getExpertDiscriptionDetail() {
		return expertDiscriptionDetail;
	}
	public void setExpertDiscriptionDetail(String expertDiscriptionDetail) {
		this.expertDiscriptionDetail = expertDiscriptionDetail == null ? null
				: expertDiscriptionDetail.trim();
	}
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
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
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
}