package com.gtercn.carhome.web.api.utils;

import com.aliyuncs.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import com.gtercn.carhome.web.api.CarHomeApiProperties;

/**
 * 阿里短信服务接口
 * 
 * @author ken 2017-3-13 下午02:53:50
 */
public final class AliSMSUtil {
	public static void main(String[] args) {
		String mobile ="18642690085";
		String tempCode=CarHomeApiProperties.MESSAGE_REGISTER_TEMPLATE;
		sendMsg(mobile,tempCode,"8411");
	}
	public static void sendMsg(String mobile,String tempCode,String validateCode){
		try {
			String signName=CarHomeApiProperties.MESSAGE_SIGN;//签名名称
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
					CarHomeApiProperties.MESSAGE_ACCESS_KEY, CarHomeApiProperties.MESSAGE_ACCESS_SECRET);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms",
					"sms.aliyuncs.com");
			IAcsClient client = new DefaultAcsClient(profile);
			SingleSendSmsRequest request = new SingleSendSmsRequest();
			request.setSignName(signName);// 控制台创建的签名名称
			request.setTemplateCode(tempCode);// 控制台创建的模板CODE
			//短信模板中的变量；数字需要转换为字符串；个人用户每个变量长度必须小于15个字符。
			request.setParamString("{\"code\":\""+validateCode+ "\",\"product\":\""+signName+"\"}");
			// request.setParamString("{}");
			request.setRecNum(mobile);// 接收号码
			SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
			System.out.println(httpResponse);
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
}
