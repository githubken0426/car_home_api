package com.gtercn.carhome.web.api.utils;

import com.gtercn.carhome.web.api.CarHomeApiProperties;


/**
 * ftp上传文件的路径设置
 * @author Administrator
 *
 * 2016-8-25 下午02:44:39
 */
public final class FtpFilePath {
	//根路径
	public static final String ROOT_PATH="carhome";
	//用户文件夹
	public static final String USERS_PATH="users";
	//头像文件夹
	public static final String AVATAR_PATH="avatar";
	//ftp ip地址
	public static final String RESOURCES_IP = CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip");
}
