package com.gtercn.carhome.web.api.form;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.gtercn.carhome.web.api.utils.CommonRegex;

public class UserInfoForm {
	private String userId;
    private String loginId;
    private String loginPhone;
    private String password;
    private String memberId;
    private String photo;
    private String name;
    private String nickname;
    private String deviceToken;
    private String deviceType;
    private String userType;
    private String userLevel;
    private String sex;
    private String birthday;
    private String qq;
    private String mail;
    private String idcardNo;
    private String deleteFlag;
    private String updateTime;
    private String insertTime;
    private String verifyCode;
    private String verifyType;
    private String oldPwd;
    private String newPwd;
    private String token;
   
    /**
     * 验证用户登陆
     * @return
     */
	public boolean checkLogin() {
		return 	StringUtils.isNotBlank(this.loginPhone)
				&& StringUtils.isNotBlank(this.password)
				&& StringUtils.isNotBlank(this.deviceType)
				&& StringUtils.isNotBlank(this.deviceToken);
	}
	/**
	 * 验证用户注册
	 * @return
	 */
	public boolean checkRegister() {
		return 	StringUtils.isNotBlank(this.loginPhone)
				&& StringUtils.isNotBlank(this.password)
				&& StringUtils.isNotBlank(this.verifyCode)
				&& StringUtils.isNotBlank(this.verifyType);
	}
	/**
	 * 验证忘记密码
	 * @return
	 */
	public boolean checkPwdForget() {
		return 	StringUtils.isNotBlank(this.loginPhone)
				&& StringUtils.isNotBlank(this.password)
				&& StringUtils.isNotBlank(this.verifyCode);
	}
	/**
	 * 验证忘记密码
	 * @return
	 */
	public boolean checkPwdChange() {
		return 	StringUtils.isNotBlank(this.loginPhone)
				&& StringUtils.isNotBlank(this.newPwd)
				&& StringUtils.isNotBlank(this.oldPwd);
	}
	/**
	 * 验证手机号
	 * @return
	 */
	public boolean checkPhone() {
		return 	StringUtils.isNotBlank(this.loginPhone);
	}
	public boolean checkPhoneFormat() {
		return Pattern.matches(CommonRegex.REGEX_MOBILE,this.loginPhone);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPhone() {
		return loginPhone;
	}

	public void setLoginPhone(String loginPhone) {
		this.loginPhone = loginPhone.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken.trim();
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType.trim();
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
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
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getVerifyType() {
		return verifyType;
	}
	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}