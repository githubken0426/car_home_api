package com.gtercn.carhome.web.api.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.gtercn.carhome.web.api.enums.ErrorCode;

public class MyExceptionHandler extends SimpleMappingExceptionResolver
		implements HandlerExceptionResolver {

	private Logger logger = Logger.getLogger(MyExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		logger.debug("MyExceptionHandler.resolveException()");
		response.setHeader("Content-type", "application/json;charset=UTF-8");
		// API业务异常处理
		if (ex instanceof ApiException) {
			try {
				ex.printStackTrace();
				logger.error(((ApiException) ex).getErrcode().getCodeMessage(),ex);
				response.getWriter().print(((ApiException) ex).getErrcode().toJsonString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 系统异常处理
		else {
			try {
				ex.printStackTrace();
				logger.error(ex.getMessage(), ex);
				response.getWriter().print(
						ErrorCode.SYS_ERR_CODE.toJsonString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ModelAndView();
	}

}