package com.gtercn.carhome.web.api;

import com.gtercn.carhome.web.api.utils.FilePathBean;
import com.gtercn.carhome.web.api.utils.FileUtil;
import com.gtercn.carhome.web.api.utils.PropertiesManager;

/**
 * 项目配置文件，都应该放在这里
 * 
 * @author leodengye
 * 
 */
public class CarHomeApiProperties {

	/**
	 * 微信支付配置文件管理器，支持热加载
	 */
	public static final PropertiesManager WX_PAY = new PropertiesManager(
			FileUtil.getClassesPath(new FilePathBean())
					+ "resources/wxPay.properties");

	/**
	 * 项目配置文件管理器，支持热加载
	 */
	public static final PropertiesManager CAR_HOME_API = new PropertiesManager(
			FileUtil.getClassesPath(new FilePathBean())
					+ "resources/carhomeApi.properties");
	/**
	 * 短信配置文件
	 */
	public static final PropertiesManager SEND_MESSAGE_CLIENT = new PropertiesManager(
			FileUtil.getClassesPath(new FilePathBean())
					+ "resources/sendMessageConfig.properties");
	// 短信key
	public static final String MESSAGE_ACCESS_KEY = SEND_MESSAGE_CLIENT
			.getValue("access_key");
	// 短信secret
	public static final String MESSAGE_ACCESS_SECRET = SEND_MESSAGE_CLIENT
			.getValue("access_secret");
	// 短信签名
	public static final String MESSAGE_SIGN = "顺驾天下";
	// 短信模版
	public static final String MESSAGE_REGISTER_TEMPLATE = SEND_MESSAGE_CLIENT
			.getValue("register_template");
	public static final String MESSAGE_PASSWORD_TEMPLATE = SEND_MESSAGE_CLIENT
			.getValue("password_template");
	

	/**
	 * 默认城市编码(阜新)
	 */
	public static final String DEFAULT_CITY_CODE = "210900";

}