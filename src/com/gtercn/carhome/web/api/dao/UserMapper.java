package com.gtercn.carhome.web.api.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gtercn.carhome.web.api.entity.User;

@Repository
public interface UserMapper {
	/**
	 * 用户登陆
	 * @param name
	 * @return
	 */
	public User appLogin(Map<String,Object> map);
	
	public List<User> getUserByAccount(Map<String,Object> map);
	
	public int getCountByAccount(Map<String,Object> map);
	/**
	 * 更新用户设备token
	 * @param deviceToken
	 */
	public void updateDeviceToken(Map<String,Object> map);
	
	public User getUserById(String userId);
	
	/**
	 * 新增
	 * @param user
	 * @return
	 */
	public int insert(User user);
	
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
	 * 获取token
	 * @param userId
	 * @return
	 */
	public String getToken(String userId);
	/**
	 * 修改用户信息
	 * 头像，昵称，真实姓名,性别
	 * @param map
	 */
	public void updateUserPersonlInfo(Map<String,Object> map);
	
    int deleteByPrimaryKey(String userId);
    
    /**
     * 个人信息
     * @param userId
     * @return
     * 2016-12-19 上午09:27:02
     */
    public User getPersonalInfo(String userId);
}