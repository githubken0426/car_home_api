package com.gtercn.carhome.web.api.utils;

import java.util.Arrays;
import java.util.List;
/**
 * 数据库存储图片等资源时，存储路径不包含服务器路径(如:/carhome/userid/img)
 * 此类用于追加ftp url，获取完整资源路径
 * @author ken
 * 2016-12-28 下午05:10:18
 */
public final class AppendServerURL {
	/**
	 * 获取并追加ftp服务器路径
	 * 可在实体类set方法中可调用此方法，追加ftp url
	 * @param paths 参数前必须有斜杠(/)
	 * @return
	 * 2016-12-28 下午05:05:23
	 */
	public static String appendFtpConfigPath(String paths){
		if(paths==null || paths.equals("")) return null;
		String result="";
		List<String> list=Arrays.asList(paths.split(","));
		for (String str : list) {
			result+=FtpFilePath.RESOURCES_IP + str+",";
		}
		result=result.substring(0,result.lastIndexOf(","));
		return result;
	}
}
