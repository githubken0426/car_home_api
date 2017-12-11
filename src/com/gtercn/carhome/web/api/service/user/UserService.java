package com.gtercn.carhome.web.api.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.dao.UserLoginlogMapper;
import com.gtercn.carhome.web.api.dao.UserMapper;
import com.gtercn.carhome.web.api.entity.User;
import com.gtercn.carhome.web.api.entity.UserLoginlog;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserMapper userDao;
	@Autowired
	private UserLoginlogMapper userLoginlogDao;
//	@Autowired
//	private IntegralMapper intergralDao;
//	@Autowired
//	private UserExtendMapper userExtendDao;
	
	@Override
	public User appLogin(Map<String,Object> map) {
		return userDao.appLogin(map);
	}

	@Override
	public List<User> getUserByAccount(Map<String, Object> map) {
		return userDao.getUserByAccount(map);
	}

	/**
	 * 更新用户设备token,记录登陆日志
	 * @param map
	 * @param loginLog
	 */
	@Override
	public void operationTokenAndLog(Map<String, Object> map,UserLoginlog loginLog) {
		userDao.updateDeviceToken(map);
		userLoginlogDao.insertLoginLog(loginLog);
	}
	
	@Override
	public User getUserById(String userId) {
		return userDao.getUserById(userId);
	}
	/**
	 * 新用户注册
	 */
	@Override
	public void userRegister(User user,UserLoginlog loginLog) {
		userDao.insert(user);
		userLoginlogDao.insertLoginLog(loginLog);
		/*intergralDao.insert(integral);
		String extendId=userExtend.getUserId();
		UserExtend extend=userExtendDao.selectByPrimaryKey(extendId);
		if(null!=extend){
			userExtendDao.updateIntegralByUserId(userExtend);
		}else{
			userExtendDao.insert(userExtend);
		}*/
	}

	@Override
	public void resetPassword(Map<String,Object> map) {
		userDao.resetPassword(map);
	}

	@Override
	public int getCountByAccount(Map<String, Object> map) {
		return userDao.getCountByAccount(map);
	}


	@Override
	public void resetLoginPhone(Map<String, Object> map) {
		userDao.resetLoginPhone(map);
	}

	@Override
	public User getPersonalInfo(String userId) {
		return userDao.getPersonalInfo(userId);
	}

	@Override
	public void updateUserPersonlInfo(Map<String,Object> map) {
		userDao.updateUserPersonlInfo(map);
	}

}
