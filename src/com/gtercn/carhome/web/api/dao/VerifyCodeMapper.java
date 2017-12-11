package com.gtercn.carhome.web.api.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gtercn.carhome.web.api.entity.VerifyCode;

@Repository
public interface VerifyCodeMapper {
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
