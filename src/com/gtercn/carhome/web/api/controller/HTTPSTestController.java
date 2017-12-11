package com.gtercn.carhome.web.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gtercn.carhome.web.api.exception.ApiException;

/**
 * 账号信息
 * 
 * @author Administrator
 * 
 *         2016-8-16 上午10:23:37
 */
@Controller
@RequestMapping(value = "/app/v1/open")
public class HTTPSTestController {
	

	/**
	 * 用户名密码登陆
	 * 
	 * @param request
	 * @return // * @throws ApiException
	 */
	@RequestMapping(value = "/security", method = RequestMethod.GET)
	public ModelAndView security(HttpServletRequest request) throws ApiException {
		System.out.println("security controller");
		ModelAndView view = new ModelAndView("/security.jsp");
		return view;
	}

	
}
