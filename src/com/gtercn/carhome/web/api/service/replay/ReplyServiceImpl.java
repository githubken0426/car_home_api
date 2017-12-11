package com.gtercn.carhome.web.api.service.replay;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.dao.ReplyMapper;
import com.gtercn.carhome.web.api.entity.Reply;

@Service(value = "replyService")
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyMapper dao;

	@Override
	public List<Reply> queryAllData(Map<String, Object> map) {
//		return dao.queryAllData(map);
		return null;
	}


	@Override
	public int updateData(Reply o) throws Exception {
		return dao.updateByPrimaryKeySelective(o);
	}

	@Override
	public int insert(Reply o) {
		return dao.insert(o);		
	}

	@Override
	public int deleteData(String id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Reply> selectByQuestionId(String questionId) {
		return dao.selectByQuestionId(questionId);
	}

	@Override
	public String selectToUserById(String id) {
		return dao.selectToUserById(id);
	}

}
