package com.gtercn.carhome.web.api.entity;


public class Favor {
    private String id;
    private String userId;
    private String favorId;
    private Integer favorType;
    private Integer deleteFlag;
    private String updateTime;
    private String insertTime;
    private String title;

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
    public String getFavorId() {
        return favorId;
    }
    public void setFavorId(String favorId) {
        this.favorId = favorId == null ? null : favorId.trim();
    }
    public Integer getFavorType() {
        return favorType;
    }
    public void setFavorType(Integer favorType) {
        this.favorType = favorType;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}