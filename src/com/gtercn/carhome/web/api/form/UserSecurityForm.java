package com.gtercn.carhome.web.api.form;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.gtercn.carhome.web.api.utils.CommonRegex;

public class UserSecurityForm {
	private String userId;
	private String loginPhone;
	private String verifyCode;
	private String oldPhone;
	private String oldVerifyCode;
	private String oldVerifyType;
	private String newPhone;
	private String newVerifyCode;
	private String newVerifyType;
	private String nickname;
	private String qq;
	private String mail;
	
	/**
	 * 绑定手机
	 * @return
	 */
	public boolean checkPhoneBind(){
		return StringUtils.isNotBlank(this.userId)
		&& StringUtils.isNotBlank(this.newPhone)
		&& StringUtils.isNotBlank(this.newVerifyCode)
		&& StringUtils.isNotBlank(this.newVerifyType);
	}
	/**
	 * 绑定手机
	 * @return
	 */
	public boolean checkPhoneUnBind(){
		return  StringUtils.isNotBlank(this.oldPhone)
		&& StringUtils.isNotBlank(this.oldVerifyCode)
		&& StringUtils.isNotBlank(this.oldVerifyType);
	}
	/**
	 * 修改昵称
	 * @return
	 */
	public @Deprecated boolean checkNicknameChange(){
		return StringUtils.isNotBlank(this.userId)&& StringUtils.isNotBlank(this.nickname);
	}
	/**
	 * 昵称只能以数字、汉字、字母和下划线(不能再开头、结尾)组成
	 * @return
	 * 2016-11-7 上午10:02:18
	 */
	public @Deprecated boolean checkNicknameLegitimate(){
		String regex="^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$";
		return Pattern.matches(regex,this.nickname);
	}
	
	/**
	 * 绑定qq
	 * @return
	 */
	public boolean checkQQBind(){
		return StringUtils.isNotBlank(this.userId)&& StringUtils.isNotBlank(this.qq);
	}
	
	/**
	 * 判断qq格式
	 * @return
	 */
	public boolean checkQQFormat(){
		return Pattern.matches(CommonRegex.REGEX_NUMBER, this.qq);
	}
	/**
	 * 绑定邮箱
	 * @return
	 */
	public boolean checkEmailBind(){
		return StringUtils.isNotBlank(this.userId)
			&& StringUtils.isNotBlank(this.mail);
	}
	/**
	 * 判断邮箱格式
	 * @return
	 */
	public boolean checkEmailFormat(){
		return Pattern.matches(CommonRegex.REGEX_EMAIL,this.mail);
	}
	
	public boolean checkPhoneFormat(String phone) {
		return Pattern.matches(CommonRegex.REGEX_MOBILE,phone);
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOldPhone() {
		return oldPhone;
	}
	public void setOldPhone(String oldPhone) {
		this.oldPhone = oldPhone;
	}
	public String getOldVerifyCode() {
		return oldVerifyCode;
	}
	public void setOldVerifyCode(String oldVerifyCode) {
		this.oldVerifyCode = oldVerifyCode;
	}
	public String getNewPhone() {
		return newPhone;
	}
	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}
	public String getNewVerifyCode() {
		return newVerifyCode;
	}
	public void setNewVerifyCode(String newVerifyCode) {
		this.newVerifyCode = newVerifyCode;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getLoginPhone() {
		return loginPhone;
	}
	public void setLoginPhone(String loginPhone) {
		this.loginPhone = loginPhone;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getOldVerifyType() {
		return oldVerifyType;
	}
	public void setOldVerifyType(String oldVerifyType) {
		this.oldVerifyType = oldVerifyType;
	}
	public String getNewVerifyType() {
		return newVerifyType;
	}
	public void setNewVerifyType(String newVerifyType) {
		this.newVerifyType = newVerifyType;
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
}
