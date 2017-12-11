package com.gtercn.carhome.web.api.enums;

import java.text.MessageFormat;

import com.gtercn.carhome.web.api.exception.ApiException;


public enum ErrorCode {
	//=======系统错误=======
	SYS_ERR_CODE(-1, "程序异常", "系统繁忙,请稍后再试..."),
	SYS_FILE_UNEXISTS(-2, "程序异常", "文件不存在"),
	SYS_DATA_ERROR(-3, "程序数据异常", "系统繁忙,请稍后再试..."),
	SYS_PARAM_NULL_ERROR(-4, "参数为空错误", "输入有误,请核对你输入的信息"),
	SYS_PICTURE_UPLOAD_ERROR(-5, "图片上传失败", "图片上传失败,请稍后再试"),
	
	//=======主页模块=======
	HOME_NULL_CODE(1000, "没有数据，请联系后台管理员","获取资源失败"),
	CITY_NULL_CODE(1001, "没有数据，请联系后台管理员","获取资源失败"),
	
	//=======救援洗车等模块=======
	RESCUE_LIST_EMPTY_ERROR(2001,"救援列表为空","抱歉！未搜索到救援公司"),
	CLEANING_LIST_EMPTY_ERROR(2002,"洗车列表为空","抱歉！未搜索到洗车公司"),
	REPAIR_LIST_EMPTY_ERROR(2003,"修车列表为空","抱歉！未搜索到修车公司"),
	MAINTAIN_LIST_EMPTY_ERROR(2004,"汽车保养列表为空","抱歉！未搜索到汽车保养公司"),
	TYRE_LIST_EMPTY_ERROR(2005,"更换轮胎列表为空","抱歉！未搜索到更换轮胎公司"),
	RESCUE_EMPTY_ERROR(2006,"未搜索到该救援公司","抱歉！未搜索到该救援公司"),
	SHOP_EMPTY_ERROR(2007,"未检索到商家","抱歉！未检索到商家"),
	
	//=======达人模块=======
	EXPERT_REPLY_CONTENT_NULL(3001,"回复内容不能为空","请输入回复内容"),
	EXPERT_QUESTION_CONTENT_NULL(3002,"提问内容不能为空","请输入提问内容"),
	
	//=======用户模块=======
	USER_PARAM_NULL_ERROR(6001,"参数为空错误","输入有误,请核对你输入的信息"),
	
	//USER_AVATAR_FORMAT_ERROR(6005,"参数格式错误","请选择头像"),
	USER_PHONE_FORMAT_ERROR(6006,"参数格式错误","请输入正确的手机号"),
	USER_NICKNAME_FORMAT_ERROR(6007,"参数格式错误","昵称不能包含特殊字符"),
	
	USER_LOGIN_SUCCESS(6020,"登录成功","欢迎回来"),
	USER_LOGIN_PASSWORD_ERROR(6021,"登录错误","请输入正确的密码"),
	USER_LOGIN_OTHER_ERROR(6022,"登录错误","已在别的设备登录"),
	USER_PASSWORD_ERROR(6023,"密码错误","原密码输入有误"),
	
	USER_ACCOUNT_UNREGISTER(6030,"账号错误","手机号未注册"),
	USER_ACCOUNT_REGISTER(6031,"账号错误","手机号已注册"),
	
	USER_VERIFY_ERROR(6040,"验证码错误","请输入正确的验证码"),
	USER_AVATAR_ERROR(6041,"用户头像错误","用户头像不存在"),
	USER_AVATAR_UPLOAD_ERROR(6042,"头像上传失败","头像上传失败"),
	//=======分享模块=======
	SHARE_CODE(7000, "分享模块错误代码","用户提示信息"),
	
	//=======自驾游模块=======
	SELF_LIST_EMPTY_ERROR(8000, "自驾游列表为空","未搜索到自驾游活动"),
	COMMENT_LIST_EMPTY_ERROR(8001, "自驾游评论列表为空","未搜索到自驾游评论"),
	NOT_LOGIN_ERROR(8002, "未登录","请登录"),
	COMMENT_REPLY_EMPTY_ERROR(8003, "自驾游评论回复列表为空","未搜索到自驾游评论回复"),
	SELF_DRIVING_ID_EMPTY_ERROR(8004, "自驾游Id为空","系统繁忙,请稍后再试..."),
	ITEM_ID_EMPTY_ERROR(8005, "自驾游评论Id为空","系统繁忙,请稍后再试..."),
	CONTENT_EMPTY_ERROR(8006, "自驾游评论内容为空","请填写评论内容"),
	TO_USERID_EMPTY_ERROR(8007, "回复对象为空","请选择回复的对象"),
	SEARCH_NAME_EMPTY_ERROR(8008, "查看名单为空","请选择查看名单"),
	ACTIVITY_TIME_EMPTY_ERROR(8009, "活动时间为空","请选择活动时间"),
	//=======已经反馈=======
	FEEDBACK_CONTENT_ERROR(9000,"内容不能为空","请输入内容"),
	//=======API签名认证=======
	API_EXPIRED_CODE(13000, "请求过期", "系统繁忙,请稍后再试..."),
	API_FAILD_SINGNOMACH_CODE(13001, "签名不匹配", "系统繁忙,请稍后再试...");
	//===========================
	
	
	private final int code;
	private final String codeMessage;
	private String message;
	
	public int getCode() {
		return code;
	}
	public String getCodeMessage() {
		return codeMessage;
	}
	public String getMessage() {
		return message;
	}
	
	public ErrorCode setTemplateMsg(String... args) {
		message = MessageFormat.format(getMessage(), args);
		return this;
	}
	
	
	private ErrorCode(int code, String codeMessage, String message) {
		this.code = code;
		this.codeMessage = codeMessage;
		this.message = message;
	}
	
	
	public static void main(String[] args) throws ApiException{
		System.out.println(ErrorCode.SYS_ERR_CODE.setTemplateMsg("中国人", "北京", "22").toJsonString());
	}
	
	public String toJsonString() {
	return "{\"err_code\":\"" +code + "\",\"err_message\":\"" + codeMessage + "\",\"message\":\""+message+"\"}";
	}
	
	
}