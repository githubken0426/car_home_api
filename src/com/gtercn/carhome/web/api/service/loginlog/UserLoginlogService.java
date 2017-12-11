package com.gtercn.carhome.web.api.service.loginlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.dao.UserLoginlogMapper;
import com.gtercn.carhome.web.api.entity.UserLoginlog;

@Service
public class UserLoginlogService implements IUserLoginlogService {
	@Autowired
	private UserLoginlogMapper userLoginlogDao;
	
	
	@Override
	public UserLoginlog getLastLogByUserId(String userId) {
		return userLoginlogDao.getLastLogByUserId(userId);
	}

	@Override
	public void updateLoginTime(String logId) {
		userLoginlogDao.updateLoginTime(logId);
	}

	@Override
	public void updateLastLogbyUserId(String userId) {
		userLoginlogDao.updateLastLogbyUserId(userId);		
	}

}
