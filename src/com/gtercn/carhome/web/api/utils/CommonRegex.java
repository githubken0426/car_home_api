package com.gtercn.carhome.web.api.utils;

/**
 * 正则表达式验证
 * @author Administrator
 *
 * 2016-8-9 上午08:34:45
 */
public final class CommonRegex {
	// 验证用户名（以字母开头，长度在6-18之间，只能包含字母、数字和下划线）
	public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";
	// 验证密码6-20位数字、字母区分大小写
	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";
	// 验证码6位数字写
	public static final String REGEX_NUMBER = "^[0-9]*$";
	// 验证手机号
	public static final String REGEX_MOBILE = "^0?(13[0-9]|15[012356789]|17[012356789]|18[0236789]|14[57])[0-9]{8}$";
	// 验证身份证
	public static final String REGEX_ID_CARD = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
	// 验证邮箱
	public static final String REGEX_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	// 验证昵称
	public static final String NICKNAME="^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$";
}
