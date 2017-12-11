package com.gtercn.carhome.web.api.dao;

import org.springframework.stereotype.Repository;

import com.gtercn.carhome.web.api.entity.UserLoginlog;

@Repository
public interface UserLoginlogMapper {
    /**
	 * 插入登陆日志
	 * @param loginLog
	 */
	public void insertLoginLog(UserLoginlog loginLog);
	
	/**
	 * 根据userid获取最近一次的登陆日志
	 * @param userId
	 * @return
	 */
	public UserLoginlog getLastLogByUserId(String userId);
	/**
	 * 根据userid修改最近一次的登陆日志
	 * @param userId
	 */
	public void updateLastLogbyUserId(String userId);
	/**
	 * 更新登陆时间
	 * @param map
	 */
	public void updateLoginTime(String logId);
	
}