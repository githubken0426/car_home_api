package com.gtercn.carhome.web.api.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import com.gtercn.carhome.web.api.CarHomeApiProperties;

/**
 * 中正云通信短信接口
 * 
 * @author ken 2017-1-11 上午09:04:20
 */
public final class SendMessageClient {
	public static String post(String path, String params) throws Exception {
		BufferedReader in = null;
		PrintWriter out = null;
		HttpURLConnection httpConn = null;
		try {
			URL url = new URL(path);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);

			out = new PrintWriter(httpConn.getOutputStream());
			out.println(params);
			out.flush();

			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				StringBuffer content = new StringBuffer();
				String tempStr = "";
				in = new BufferedReader(new InputStreamReader(httpConn
						.getInputStream()));
				while ((tempStr = in.readLine()) != null) {
					content.append(tempStr);
				}
				return content.toString();
			} else {
				throw new Exception("请求出现了问题!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			in.close();
			out.close();
			httpConn.disconnect();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		String account=CarHomeApiProperties.SEND_MESSAGE_CLIENT.getValue("account")+"abc";
		String password=CarHomeApiProperties.SEND_MESSAGE_CLIENT.getValue("password");
		String apiUrl=CarHomeApiProperties.SEND_MESSAGE_CLIENT.getValue("api_url");
		String label=CarHomeApiProperties.SEND_MESSAGE_CLIENT.getValue4GBK("message_label");
		String phone="18642690085";
		String content="您好，您的验证码是884192"+label;
		content+=URLEncoder.encode(content, "gb2312");
		String time=DateFormateUtil.formatDateTime(new Date(), "yyyy-MM-dd hh:mm");
		
		String resMessage = SendMessageClient.post(
				apiUrl,"id="+account+"&pwd="+password+"&to="+phone+"&content="
						+ content + "&time="+time);
		System.out.println(resMessage);
	}
}
