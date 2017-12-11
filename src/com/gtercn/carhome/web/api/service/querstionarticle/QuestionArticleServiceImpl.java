package com.gtercn.carhome.web.api.service.querstionarticle;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.dao.QuestionArticleMapper;
import com.gtercn.carhome.web.api.entity.QuestionArticle;


@Service(value = "questionArticleService")
public class QuestionArticleServiceImpl implements QuestionArticleService {
	@Autowired
	private QuestionArticleMapper dao;

	@Override
	public List<QuestionArticle> queryQuestionCondition(Map<String, Object> map) {
		return dao.queryQuestionCondition(map);
	}

	@Override
	public int insertQuestion(com.gtercn.carhome.web.api.entity.write.QuestionArticle o) {
		return dao.insertQuestion(o);		
	}

	@Override
	public int deleteData(String id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertArticl(com.gtercn.carhome.web.api.entity.write.QuestionArticle record) {
		return dao.insertArticl(record);
	}

	@Override
	public List<QuestionArticle> queryMyArticleQuestion(Map<String, Object> map) {
		return dao.queryMyArticleQuestion(map);
	}

	@Override
	public QuestionArticle selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public String selectToUserById(String id) {
		return dao.selectToUserById(id);
	}

	@Override
	public List<QuestionArticle> queryArticleCondition(Map<String, Object> map) {
		return dao.queryArticleCondition(map);
	}

	@Override
	public int updateGlaceNumber(String articleId) {
		return dao.updateGlaceNumber(articleId);
	}

	@Override
	public QuestionArticle queryArticleDetail(Map<String, Object> map) {
		return dao.queryArticleDetail(map);
	}

	@Override
	public QuestionArticle queryQuestionDetail(Map<String, Object> map) {
		return dao.queryQuestionDetail(map);
	}
}
