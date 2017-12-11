package com.gtercn.carhome.web.api.entity;

/**
 * 验证码
 * @author Administrator
 *
 * 2016-8-12 上午10:55:11
 */
public class VerifyCode {
	private String verifyId;
	private String verifyCode;//验证码
	private Integer verifyExpire;//失效时间
	private Integer verifyType;//0注册 1忘记密码 2手机号解绑 3手机号绑定
	private String insertTime;
	private String phone;
	
	public String getVerifyId() {
		return verifyId;
	}
	public void setVerifyId(String verifyId) {
		this.verifyId = verifyId;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public Integer getVerifyExpire() {
		return verifyExpire;
	}
	public void setVerifyExpire(Integer verifyExpire) {
		this.verifyExpire = verifyExpire;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Integer getVerifyType() {
		return verifyType;
	}
	public void setVerifyType(Integer verifyType) {
		this.verifyType = verifyType;
	}
}
