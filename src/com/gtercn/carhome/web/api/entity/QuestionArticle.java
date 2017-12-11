package com.gtercn.carhome.web.api.entity;

import com.gtercn.carhome.web.api.utils.AppendServerURL;
import com.gtercn.carhome.web.api.utils.FtpFilePath;

public class QuestionArticle {
	private String id;
	private String userId;
	private Integer type;
	private String title;
	private Integer supportNumber;
	private Integer favorNumber;
	private Integer glanceNumber;
	private String content;
	private String resUrlList;
	private Integer deleteFlag;
	private String insertTime;
	private String updateTime;
	private String publishTime;// 已发表时间
	private String expertName;// 达人名字
	private String expertPortrait;// 达人头像
	private String userPortrait;// 用户头像
	private String replyNum;// 问题回复数量
	private String motto;// 达人座右铭
	private String telephoneNumber;// 达人电话
	private String expertWechatNumber;// 微信号
	private String nickname;// 达人昵称
	private String isFavored;
	private String htmlUrl;
	private String introduction;
	private String cityCode;

	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		String url = (htmlUrl != null && htmlUrl != "") ? FtpFilePath.RESOURCES_IP
				+ htmlUrl
				: htmlUrl;
		this.htmlUrl = url;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime == null ? null : publishTime.trim();
	}

	public Integer getSupportNumber() {
		return supportNumber == null ? 0 : supportNumber;
	}

	public void setSupportNumber(Integer supportNumber) {
		this.supportNumber = supportNumber;
	}

	public Integer getFavorNumber() {
		return favorNumber == null ? 0 : favorNumber;
	}

	public void setFavorNumber(Integer favorNumber) {
		this.favorNumber = favorNumber == null ? 0 : favorNumber;
	}

	public Integer getGlanceNumber() {
		return glanceNumber == null ? 0 : glanceNumber;
	}

	public void setGlanceNumber(Integer glanceNumber) {
		this.glanceNumber = glanceNumber;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getResUrlList() {
		return resUrlList;
	}

	public void setResUrlList(String resUrlList) {
		this.resUrlList = AppendServerURL.appendFtpConfigPath(resUrlList);
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

	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}

	public String getExpertPortrait() {
		return expertPortrait;
	}

	public void setExpertPortrait(String expertPortrait) {
		String url = (expertPortrait != null && expertPortrait != "") ? FtpFilePath.RESOURCES_IP
				+ expertPortrait
				: expertPortrait;
		this.expertPortrait = url;
	}

	public String getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(String replyNum) {
		this.replyNum = replyNum;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getExpertWechatNumber() {
		return expertWechatNumber;
	}

	public void setExpertWechatNumber(String expertWechatNumber) {
		this.expertWechatNumber = expertWechatNumber;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIsFavored() {
		return isFavored;
	}

	public void setIsFavored(String isFavored) {
		this.isFavored = isFavored;
	}

	public String getUserPortrait() {
		return userPortrait;
	}

	public void setUserPortrait(String userPortrait) {
		String url = (userPortrait != null && userPortrait != "") ? FtpFilePath.RESOURCES_IP
				+ userPortrait
				: userPortrait;
		this.userPortrait = url;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
}