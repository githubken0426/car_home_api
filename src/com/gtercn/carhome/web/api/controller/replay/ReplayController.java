package com.gtercn.carhome.web.api.controller.replay;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtercn.carhome.web.api.entity.Reply;
import com.gtercn.carhome.web.api.enums.ErrorCode;
import com.gtercn.carhome.web.api.exception.ApiException;
import com.gtercn.carhome.web.api.form.QuestionArticleForm;
import com.gtercn.carhome.web.api.form.ReplyForm;
import com.gtercn.carhome.web.api.service.querstionarticle.QuestionArticleService;
import com.gtercn.carhome.web.api.service.replay.ReplyService;
import com.gtercn.carhome.web.api.utils.CommonUtil;
import com.gtercn.carhome.web.api.utils.Encrypt;
import com.gtercn.carhome.web.api.view.JsonList;
import com.gtercn.carhome.web.api.view.JsonView;

/**
 * 问题回复相关
 * 
 * @author ken
 * 2016-12-14 上午11:02:39
 */
@Controller
@RequestMapping("/app/v1/expert/reply")
public class ReplayController {
	@Autowired
	private ReplyService replyService;
	@Autowired
	private QuestionArticleService questionArticleService;
	
	/**
	 * 查询回复列表
	 * @param request
	 * @return
	 * 2016-12-14 上午08:38:04
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public JsonList<Reply> queryTopData(@RequestBody QuestionArticleForm form,
			HttpServletRequest request) throws ApiException{
		JsonList<Reply> view = new JsonList<Reply>();
		String questionId=form.getQuestionId();
		List<Reply> replayList=replyService.selectByQuestionId(questionId);
		view.setResult(replayList);
		if(replayList.size()==0){
			view.setMessage("你还没有回复");
		}
		return view;
	}

	/**
	 * 问题墙回复
	 * @param form
	 * @param request
	 * @return
	 * @throws ApiException
	 * 2016-12-16 上午08:46:31
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonView replyAdd(@RequestBody ReplyForm form,Reply reply,
			HttpServletRequest request) throws ApiException{
		JsonView view = new JsonView();
		if(!form.checkReply()){
			throw new ApiException(ErrorCode.EXPERT_REPLY_CONTENT_NULL);
		}
		reply.setId(CommonUtil.getUID());
		String token =request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		reply.setUserId(userId);
		String questionId=form.getQuestionId();
		reply.setReferenceId(questionId);
		String typeId=form.getTypeId();
		int type=(typeId!=null && typeId!="")?Integer.valueOf(typeId):0;//0代表问题墙
		reply.setTypeId(type);
		String id=form.getId();
		reply.setItemId(id);
		String toUserId="";
		if(id!=null && id!=""){//针对用户回复
			toUserId=replyService.selectToUserById(id);
		}else{//针对回复问题
			toUserId=questionArticleService.selectToUserById(questionId);
		}
		reply.setToUserId(toUserId);
		String content=form.getContent();
		reply.setContent(content);
		replyService.insert(reply);
		view.setMessage("成功！");
		return view;
	}
}
