package com.gtercn.carhome.web.api.form;


public class SelfDrivingCommentForm {
	
    private String selfDrivingId;//自驾游Id
    private String itemId;//自驾游主评论Id
    private String content;//自驾游评论内容
    private String toUserId;//回复的用户
    private String title;//标题

	public String getSelfDrivingId() {
		return selfDrivingId;
	}

	public void setSelfDrivingId(String selfDrivingId) {
		this.selfDrivingId = selfDrivingId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}