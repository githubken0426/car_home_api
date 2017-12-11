package com.gtercn.carhome.web.api.dao;

import java.util.List;

import com.gtercn.carhome.web.api.entity.Reply;

public interface ReplyMapper {
    int deleteByPrimaryKey(String id);
    /**
     * 插入数据
     * @param record
     * @return
     * 2016-12-16 上午09:26:05
     */
    int insert(Reply record);

    int insertSelective(Reply record);

    
   
    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);
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
    String selectToUserById(String id);
   
}