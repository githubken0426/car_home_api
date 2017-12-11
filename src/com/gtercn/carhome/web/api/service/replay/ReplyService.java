package com.gtercn.carhome.web.api.service.replay;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.web.api.entity.Reply;

public interface ReplyService {

	/**
	 * 查询
	 * 
	 * @return
	 */
	public List<Reply> queryAllData(Map<String, Object> map);

	/**
	 * 新增
	 * 
	 * @param o
	 * @return
	 */
	public int insert(Reply o);

	/**
	 * 修改
	 * 
	 * @param o
	 * @return
	 */
	public int updateData(Reply o) throws Exception;

	/**
	 * 删除
	 * 
	 * @param o
	 * @return
	 */
	public int deleteData(String id) throws Exception;
	
	/**
     * 查询回复列表
     * @param questionId
     * @return
     * 2016-12-15 下午02:24:48
     */
	List<Reply> selectByQuestionId(String questionId);
	
	/**
     * 通过item_id获取toUserId
     * @param itemId
     * @return
     * 2016-12-27 上午11:26:02
     */
    String selectToUserById(String itemId);
	
}
