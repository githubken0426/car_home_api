package com.gtercn.carhome.web.api.controller.questionarticle;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gtercn.carhome.web.api.CarHomeApiProperties;
import com.gtercn.carhome.web.api.entity.QuestionArticle;
import com.gtercn.carhome.web.api.enums.ErrorCode;
import com.gtercn.carhome.web.api.exception.ApiException;
import com.gtercn.carhome.web.api.form.IdForm;
import com.gtercn.carhome.web.api.service.querstionarticle.QuestionArticleService;
import com.gtercn.carhome.web.api.utils.CommonUtil;
import com.gtercn.carhome.web.api.utils.Encrypt;
import com.gtercn.carhome.web.api.utils.FtpFileTools;
import com.gtercn.carhome.web.api.view.JsonList;
import com.gtercn.carhome.web.api.view.JsonObjectView;
import com.gtercn.carhome.web.api.view.JsonView;

/**
 * 问题墙 文章
 * 
 * @author ken 2016-12-14 上午11:02:39
 */
@Controller
@RequestMapping("/app/v1/expert")
public class QuestionArticleController {
	@Autowired
	private QuestionArticleService questionArticleService;

	/**
	 * 查询问题墙
	 * 
	 * @param request
	 * @return 2016-12-14 上午08:38:04
	 */
	@ResponseBody
	@RequestMapping(value = "/open/question/list", method = RequestMethod.POST)
	public JsonList<QuestionArticle> queryAllQuestion(HttpServletRequest request)
			throws ApiException {
		JsonList<QuestionArticle> jv = new JsonList<QuestionArticle>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<QuestionArticle> list = questionArticleService
				.queryQuestionCondition(map);
		jv.setResult(list);
		if(list.size()==0){
			jv.setMessage("没有找到数据");
		}
		return jv;
	}
	/**
	 * 问题详情
	 * @param id
	 * @param request
	 * @return
	 * @throws ApiException
	 * 2017-3-13 下午05:19:21
	 */
	@ResponseBody
	@RequestMapping(value = "/open/question/query", method = RequestMethod.POST)
	public JsonObjectView queryQuestionDetail(@RequestBody IdForm form,
			HttpServletRequest request)throws ApiException {
		JsonObjectView view = new JsonObjectView();
		Map<String,Object> map=new HashMap<String,Object>();
		String token =request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		map.put("userId", userId);
		String id=form.getId();
		map.put("id", id);
		QuestionArticle question =questionArticleService.queryQuestionDetail(map);
		view.setResult(question);
		return view;
	}

	/**
	 * 问题墙-》提问
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws ApiException
	 *             2016-12-16 上午08:46:31
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/question/add", method = RequestMethod.POST)
	public JsonView questionAdd(HttpServletRequest request,
			com.gtercn.carhome.web.api.entity.write.QuestionArticle question, @RequestParam("content") String content,
			@RequestParam("type") String type) throws ApiException, IOException {
		JsonView view = new JsonView();
		if(content==null ||content==""){
			throw new ApiException(ErrorCode.EXPERT_QUESTION_CONTENT_NULL);
		}
		String token =request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		String questionId = CommonUtil.getUID();
		question.setId(questionId);
		question.setUserId(userId);
		question.setContent(content);
		int typeId = (type != null && type != "") ? Integer.valueOf(type) : 1;
		question.setType(typeId);

		String[] savePath = { "carhome", "question", questionId };
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		//获取上传文件的路径数组
		String resUrlList = FtpFileTools.saveFileAndGetUrl(savePath, multipartRequest);
		question.setResUrlList(resUrlList);
		
		questionArticleService.insertQuestion(question);
		view.setMessage("发表成功");
		return view;
	}

	/**
	 * 查询文章
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws ApiException
	 *             2016-12-14 下午04:07:53
	 */
	@ResponseBody
	@RequestMapping(value = "/open/article/list", method = RequestMethod.POST)
	public JsonList<QuestionArticle> queryAllArticle(HttpServletRequest request)
			throws ApiException {
		JsonList<QuestionArticle> jv = new JsonList<QuestionArticle>();
		Map<String, Object> map = new HashMap<String, Object>();
		String token =request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		map.put("userId", userId);
		String cityCode =request.getParameter("city_code");
		if(!StringUtils.isNotBlank(cityCode))
			cityCode=CarHomeApiProperties.DEFAULT_CITY_CODE;
		map.put("cityCode", cityCode);
		List<QuestionArticle> list = questionArticleService
				.queryArticleCondition(map);
		jv.setResult(list);
		if(list.size()==0){
			jv.setMessage("没有找到数据");
		}
		return jv;
	}
	/**
	 * 文章详情
	 * @param id
	 * @param request
	 * @return
	 * @throws ApiException
	 * 2017-3-13 下午05:19:21
	 */
	@ResponseBody
	@RequestMapping(value = "/open/article/query", method = RequestMethod.POST)
	public JsonObjectView queryQrticleDetail(@RequestBody IdForm form,
			HttpServletRequest request)throws ApiException {
		JsonObjectView view = new JsonObjectView();
		Map<String,Object> map=new HashMap<String,Object>();
		String token =request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		map.put("userId", userId);
		String id=form.getId();
		map.put("id", id);
		QuestionArticle question =questionArticleService.queryArticleDetail(map);
		view.setResult(question);
		return view;
	}
	/**
	 * 更新浏览数量
	 * @param request
	 * @return
	 * 2017-3-10 下午02:38:25
	 */
	@ResponseBody
	@RequestMapping(value = "/open/article/glancenum", method = RequestMethod.POST)
	public JsonView articleGlanceNum(HttpServletRequest request){
		JsonView view = new JsonView();
		Map<String,Object> map=new HashMap<String,Object>();
		String id =request.getParameter("article_id"); 
		questionArticleService.updateGlaceNumber(id);
		QuestionArticle article=questionArticleService.selectByPrimaryKey(id);
		int glanceNumber=(article==null)? 0:article.getGlanceNumber();
		map.put("glance_number", glanceNumber);
		view.setResult(map);
		return view;
	}
	
}
