package com.gtercn.carhome.web.api.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * sosoapi跨域请求
 * @author Administrator
 *
 * 2016-8-4 下午01:37:51
 */
public class SoSoAPIFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse)response;
		//"*"存在风险，建议指定可信任的域名来接收响应信息，如"http://www.sosoapi.com"
		resp.addHeader("Access-Control-Allow-Origin", "*");
		//如果存在自定义的header参数，需要在此处添加，逗号分隔
		resp.addHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, "
				+ "If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, "
				+ "Content-Type, X-E4M-With");
		resp.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");  
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
