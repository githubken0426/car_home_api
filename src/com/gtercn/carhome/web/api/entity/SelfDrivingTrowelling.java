package com.gtercn.carhome.web.api.entity;

import java.util.List;

public class SelfDrivingTrowelling {
    private String id;
    
    private String userId;

    private String title;

    private String content;
    
    private String picUrls;

    private List<String> picUrlsList;

    private Integer availableFlag;

    private Integer deleteFlag;

    private String updateTime;

    private String insertTime;
    
    private Integer signFlag;

    private Integer collectionFlag;
    
    private String nickname;
    
    private String avatarUrl;
    
    private String cityCode;
    
    private String enrollFlag;//报名flag
    
    private String publicFlag;//发布flag
    
    private String activityTime;//活动时间
    
    private Integer count;//查看名单人数

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
		this.userId = userId;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public List<String> getPicUrlsList() {
		return picUrlsList;
	}

	public void setPicUrlsList(List<String> picUrlsList) {
		this.picUrlsList = picUrlsList;
	}

	public Integer getAvailableFlag() {
        return availableFlag;
    }

    public void setAvailableFlag(Integer availableFlag) {
        this.availableFlag = availableFlag;
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
    
    public Integer getSignFlag() {
        return signFlag;
    }

    public void setSignFlag(Integer signFlag) {
        this.signFlag = signFlag;
    }

    public Integer getCollectionFlag() {
        return collectionFlag;
    }

    public void setCollectionFlag(Integer collectionFlag) {
        this.collectionFlag = collectionFlag;
    }

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getEnrollFlag() {
		return enrollFlag;
	}

	public void setEnrollFlag(String enrollFlag) {
		this.enrollFlag = enrollFlag;
	}

	public String getPublicFlag() {
		return publicFlag;
	}

	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
	}

	public String getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}