package com.gtercn.carhome.web.api.service.verifycode;

import java.util.Map;

import com.gtercn.carhome.web.api.entity.VerifyCode;

public interface IVerifyCodeService {
	/**
	 * 插入
	 * @param verify
	 */
	public void insert(VerifyCode verify);
	
	/**
	 * 检测出验证码
	 * @param map
	 * @return
	 */
	public VerifyCode getByCondition(Map<String,Object> map);
	
}
