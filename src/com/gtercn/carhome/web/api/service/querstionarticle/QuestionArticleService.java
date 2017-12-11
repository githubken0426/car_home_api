package com.gtercn.carhome.web.api.service.querstionarticle;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.web.api.entity.QuestionArticle;

public interface QuestionArticleService {

	/**
	 * 文章详情
	 * @param map
	 * @return
	 * 2017-3-14 上午09:08:03
	 */
	QuestionArticle queryArticleDetail(Map<String,Object> map);
	/**
	 * 问题详情
	 * @param map
	 * @return
	 * 2017-3-14 上午09:28:04
	 */
	QuestionArticle queryQuestionDetail(Map<String,Object> map);
	
	/**
	 * 主键查询
	 * @param id
	 * @return
	 * 2016-12-27 下午01:17:19
	 */
	QuestionArticle selectByPrimaryKey(String id);
	

	/**
	 * 新增 提问
	 * @param o
	 * @return
	 */
	public int insertQuestion(com.gtercn.carhome.web.api.entity.write.QuestionArticle o) ;
	/**
	 * 新增文章
	 * @param record
	 * @return
	 * 2016-12-16 下午03:32:13
	 */
	public int insertArticl(com.gtercn.carhome.web.api.entity.write.QuestionArticle record);

	/**
	 * 修改收藏数量
	 * 
	 * @param o
	 * @return
	int updateFavorNum(String favorNum);
	int updateFavorNumById(String id,String favorNum);*/
	/**
	 * 删除
	 * 
	 * @param o
	 * @return
	 */
	public int deleteData(String id) throws Exception;
	
	/**
	 * 查询问题墙
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
	 * 主键查询,返回提问者id
	 * @param id
	 * @return
	 * 2016-12-27 下午01:17:19
	 */
	String selectToUserById(String id);
	
	int updateGlaceNumber(String articleId);
}
