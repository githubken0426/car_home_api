package com.gtercn.carhome.web.api.dao;

import org.springframework.stereotype.Repository;

import com.gtercn.carhome.web.api.entity.Feedback;

@Repository
public interface FeedbackMapper {
    //意见反馈上报
    public void insert(Feedback record);
    //意见反馈上报

    Feedback selectByPrimaryKey(String feedbackId);

    int updateByPrimaryKey(Feedback record);
}