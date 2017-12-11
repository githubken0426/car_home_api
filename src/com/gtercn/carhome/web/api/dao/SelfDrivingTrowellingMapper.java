package com.gtercn.carhome.web.api.dao;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.web.api.entity.Reply;
import com.gtercn.carhome.web.api.entity.SelfDrivingTrowelling;

public interface SelfDrivingTrowellingMapper {
    int deleteByPrimaryKey(String id);

    int insert(SelfDrivingTrowelling record);

    int insertSelective(SelfDrivingTrowelling record);

    SelfDrivingTrowelling selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SelfDrivingTrowelling record);

    int updateByPrimaryKey(SelfDrivingTrowelling record);
    
    /**
     * 获取自驾游信息列表(未登录状态下)
     * @param map 参数
     * @return
     */
    List<SelfDrivingTrowelling> getInfoListNoLogin(Map<String, Object> map);
    
    /**
     * 获取自驾游信息列表(登录状态下)
     * @param map 参数
     * @return
     */
    List<SelfDrivingTrowelling> getInfoList(Map<String, Object> map);
    
    /**
     * 获取自驾游信息(登录状态下)
     * @param map 参数
     * @return
     */
    SelfDrivingTrowelling getInfoOne(Map<String, Object> map);
    
    /**
     * 获取自驾游评论
     * @param map 参数
     * @return
     */
    List<Reply> getCommentList(Map<String, Object> map);
    
    /**
     * 展开评论获取该评论下的所有评论
     * @param map 参数
     * @return
     */
    List<Reply> getCommentReply(Map<String, Object> map);
    
    /**
     * 对自驾游的主评论
     * @param map 参数
     * @return
     */
    Reply insertComment(Map<String, Object> map);
    
    /**
     * 获取我的活动（自驾游）
     * @param map 参数
     * @return
     */
    List<SelfDrivingTrowelling> getActivityList(Map<String, Object> map);
    
    /**
     * 获取查看名单
     * @param map 参数
     * @return
     */
    List<SelfDrivingTrowelling> getSearchName(String selfDrivingId);
    
    /**
     * 获取查看名单人数
     * @param map 参数
     * @return
     */
    Integer getCount(String selfDrivingId);
}