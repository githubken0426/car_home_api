package com.gtercn.carhome.web.api.controller.feedback;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtercn.carhome.web.api.entity.Feedback;
import com.gtercn.carhome.web.api.enums.ErrorCode;
import com.gtercn.carhome.web.api.exception.ApiException;
import com.gtercn.carhome.web.api.form.FeedbackForm;
import com.gtercn.carhome.web.api.service.feedback.FeedbackService;
import com.gtercn.carhome.web.api.utils.CommonUtil;
import com.gtercn.carhome.web.api.utils.Encrypt;
import com.gtercn.carhome.web.api.view.JsonObjectView;

@Controller
@RequestMapping(value = "/app/v1/feedback")
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;

	@ResponseBody
	@RequestMapping(value = "/advice", method = RequestMethod.POST)
	public JsonObjectView insertFeedback(@RequestBody FeedbackForm form,
			Feedback feedback, HttpServletRequest request) throws ApiException {
		JsonObjectView json = new JsonObjectView();
		String content=form.getContent();
		if(!StringUtils.isNotBlank(content))
			throw new ApiException(ErrorCode.FEEDBACK_CONTENT_ERROR);
		feedback.setId(CommonUtil.getUID());
		String token = request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		feedback.setUserId(userId);
		feedback.setContent(content);
		feedback.setMail(form.getMail());
		feedbackService.insert(feedback);
		json.setMessage("您的建议已收到，我们将尽快处理！");
		return json;
	}
}
