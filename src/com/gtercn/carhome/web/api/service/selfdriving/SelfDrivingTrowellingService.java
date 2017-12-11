package com.gtercn.carhome.web.api.service.selfdriving;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.gtercn.carhome.web.api.entity.Reply;
import com.gtercn.carhome.web.api.entity.SelfDrivingExtend;
import com.gtercn.carhome.web.api.entity.SelfDrivingTrowelling;
import com.gtercn.carhome.web.api.exception.ApiException;

public interface SelfDrivingTrowellingService {

	
	/**
	 * 获取自驾游列表信息
	 * @return
	 * @throws Exception
	 */
	public List<SelfDrivingTrowelling> getInfoList(String userId, String cityCode);
	
	/**
	 * 获取自驾游信息
	 * @return
	 * @throws Exception
	 */
	public SelfDrivingTrowelling getInfoOne(String userId, String id);
	
	/**
	 * 获取自驾游评论列表信息
	 * @return
	 * @throws Exception
	 */
	public List<Reply> getCommentList(String selfDrivingId);
	
	/**
	 * 展开评论获取该评论下的所有评论
	 * @return
	 * @throws Exception
	 */
	public List<Reply> getCommentReply(String selfDrivingId, String itemId);
	
	/**
	 * 对自驾游的主评论
	 * @return
	 * @throws Exception
	 */
	public int getComment(String userId, String selfDrivingId, String content);
	
	/**
	 * 对自驾游的子评论
	 * @return
	 * @throws Exception
	 */
	public int getCommentSon(String userId, String selfDrivingId, String content, String toUserId, String itemId);
	
	/**
	 * 对自驾游的子评论
	 * @return
	 * @throws Exception
	 */
	public abstract SelfDrivingTrowelling getRelease(List<MultipartFile> list, Map<String, String> map) throws ApiException;
	
	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public SelfDrivingTrowelling getDataById(String id) throws Exception;

	/**
	 * 新增
	 * 
	 * @param o
	 * @return
	 */
	public int addData(SelfDrivingTrowelling o) throws Exception;

	/**
	 * 修改
	 * 
	 * @param o
	 * @return
	 */
	public int updateData(SelfDrivingTrowelling o) throws Exception;

	/**
	 * 删除
	 * 
	 * @param o
	 * @return
	 */
	public int deleteData(String id) throws Exception;
	
	/**
	 * 自驾游报名
	 * 
	 * @param o
	 * @return
	 */
	public int addEnrolData(SelfDrivingExtend o) throws Exception;
	
	/**
	 * 自驾游报名解除
	 * 
	 * @param o
	 * @return
	 */
	public int removeEnrolData(String selfDrivingId, String userId) throws Exception;
	
	/**
	 * 自驾游取消
	 * 
	 * @param o
	 * @return
	 */
	public int cancel(String selfDrivingId, String userId) throws Exception;
	
	/**
	 * 我的活动（自驾游）
	 * 
	 * @param o
	 * @return
	 */
	public List<SelfDrivingTrowelling> getActivityList(String userId, String cityCode);
	
	/**
	 * 查看名单
	 * 
	 * @param o
	 * @return
	 */
	public List<Object> getSearchName(String selfDrivingId);
}
