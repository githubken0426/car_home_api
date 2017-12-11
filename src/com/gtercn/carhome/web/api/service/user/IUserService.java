package com.gtercn.carhome.web.api.service.user;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.web.api.entity.User;
import com.gtercn.carhome.web.api.entity.UserLoginlog;


public interface IUserService {
	
	/**
	 * 用户登陆
	 * @param name
	 * @return
	 */
	public User appLogin(Map<String,Object> map);
	
	public List<User> getUserByAccount(Map<String,Object> map);
	
	public int getCountByAccount(Map<String,Object> map);
	/**
	 * 更新用户设备token,记录登陆日志
	 * @param map
	 * @param loginLog
	 */
	public void operationTokenAndLog(Map<String, Object> map,UserLoginlog loginLog);
	
	public User getUserById(String userId);
	/**
	 * 新用户注册
	 * @param user
	 * @param integral
	 * Integral integral,UserExtend userExtend,
	 */
	public void userRegister(User user,UserLoginlog loginLog);
	
	/**
	 * 重置密码
	 * @param userId
	 */
	public void resetPassword(Map<String,Object> map);
	
	/**
	 * 修改手机号
	 * @param map
	 */
	public void resetLoginPhone(Map<String,Object> map);
	
	/**
	 * 修改用户信息
	 * 头像，昵称，真实姓名,性别
	 * @param map
	 */
	public void updateUserPersonlInfo(Map<String,Object> map);

	/**
     * 个人信息
     * @param userId
     * @return
     * 2016-12-19 上午09:27:02
     */
    public User getPersonalInfo(String userId);
}
