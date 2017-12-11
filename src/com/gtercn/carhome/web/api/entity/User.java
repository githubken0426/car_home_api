package com.gtercn.carhome.web.api.entity;

import com.gtercn.carhome.web.api.utils.FtpFilePath;



public class User {
    private String userId;
    private String loginPhone;
    private String password;
    private String realName;
    private Integer sex;
    private String nickname;
    private String avatarUrl;
    private String deviceToken;
    private Integer deviceType;
    private String updateTime;
    private String insertTime;
    private Integer deleteFlag;
    
//    private String loginId;
//    private String memberId;
//    private String phone;
//    private String name;
//    private Integer userType;
//    private String userLevel;
//    private Integer sex;
//    private String birthday;
//    private String qq;
//    private String mail;
//    private String idcardNo;

    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getLoginPhone() {
        return loginPhone;
    }

    public void setLoginPhone(String loginPhone) {
        this.loginPhone = loginPhone == null ? null : loginPhone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken == null ? null : deviceToken.trim();
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
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

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		String url=(avatarUrl!=null && avatarUrl!="")?FtpFilePath.RESOURCES_IP+avatarUrl:avatarUrl;
		this.avatarUrl = url;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
}