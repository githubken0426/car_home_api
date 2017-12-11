package com.gtercn.carhome.web.api.service.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.dao.FeedbackMapper;
import com.gtercn.carhome.web.api.entity.Feedback;


@Service(value="feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	private FeedbackMapper entityDao;


	@Override
	public void insert(Feedback record) {
		entityDao.insert(record);	
	}

}
