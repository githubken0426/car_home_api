package com.gtercn.carhome.web.api.entity.write;

/**
 * 写入的Bean
 * 
 * @author ken
 * 2017-3-1 下午05:05:33
 */
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
	private String htmlUrl;
	private Integer deleteFlag;
	private String insertTime;
	private String updateTime;
	private String introduction;

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
		this.htmlUrl = htmlUrl;
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
		this.resUrlList = resUrlList;
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
}