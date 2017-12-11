package com.gtercn.carhome.web.api.dao;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.web.api.entity.QuestionArticle;

public interface QuestionArticleMapper {
	/**
	 * 查询 问题墙
	 * @param map
	 * @return
	 * 2016-12-14 下午03:23:55
	 */
	public List<QuestionArticle> queryQuestionCondition(Map<String,Object> map);
	
	/**
	 * 查询 文章
	 * @param map
	 * @return
	 * 2016-12-14 下午03:23:55
	 */
	public List<QuestionArticle> queryArticleCondition(Map<String,Object> map);
	/**
	 * 查询 我的问题墙或文章 
	 * @param map
	 * @return
	 * 2016-12-14 下午03:23:55
	 */
	public List<QuestionArticle> queryMyArticleQuestion(Map<String,Object> map);
	/**
	 * 新增 提问
	 * @param record
	 * @return
	 * 2016-12-16 下午02:11:03
	 */
	int insertQuestion(com.gtercn.carhome.web.api.entity.write.QuestionArticle record);
	/**
	 * 新增文章
	 * @param record
	 * @return
	 * 2016-12-16 下午03:32:13
	 */
	int insertArticl(com.gtercn.carhome.web.api.entity.write.QuestionArticle record);
	/**
	 * 主键查询,返回提问者id
	 * @param id
	 * @return
	 * 2016-12-27 下午01:17:19
	 */
	String selectToUserById(String id);
	/**
	 * 修改收藏数量
	 * @param id
	 * @return
	 * 2017-1-22 下午03:45:12
	int updateFavorNum(String favorNum);
	int updateFavorNumById(String id,String favorNum);*/
	
	QuestionArticle selectByPrimaryKey(String id);
	//文章详情
	QuestionArticle queryArticleDetail(Map<String,Object> map);
	//问题详情
	QuestionArticle queryQuestionDetail(Map<String,Object> map);
	
    int deleteByPrimaryKey(String id);

    int insertSelective(com.gtercn.carhome.web.api.entity.write.QuestionArticle record);
    
    int updateByPrimaryKey(com.gtercn.carhome.web.api.entity.write.QuestionArticle record);
    
    int updateGlaceNumber(String articleId);
}