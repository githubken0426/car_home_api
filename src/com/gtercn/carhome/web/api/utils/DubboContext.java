package com.gtercn.carhome.web.api.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gtercn.carhome.web.api.exception.ApiException;


public class DubboContext {
	/**
	 * 获取XmlWebApplicationContext
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	public static ApplicationContext getApplicationContext(
			HttpServletRequest request) throws ApiException {
		ServletContext sc = request.getSession().getServletContext();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(sc);
		return context;
	}
}
