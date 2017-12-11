package com.gtercn.carhome.web.api.form;

import org.apache.commons.lang.StringUtils;

public class ReplyForm {
	private String questionId;
	private String typeId;
	private String content;
	private String id;

	/**
	 * 验证用户登陆
	 * 
	 * @return
	 */
	public boolean checkReply() {
		return StringUtils.isNotBlank(this.questionId)
				&& StringUtils.isNotBlank(this.typeId)
				&& StringUtils.isNotBlank(this.content);
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
