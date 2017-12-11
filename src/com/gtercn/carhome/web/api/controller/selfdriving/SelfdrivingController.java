package com.gtercn.carhome.web.api.controller.selfdriving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gtercn.carhome.web.api.entity.Reply;
import com.gtercn.carhome.web.api.entity.SelfDrivingExtend;
import com.gtercn.carhome.web.api.entity.SelfDrivingTrowelling;
import com.gtercn.carhome.web.api.enums.ErrorCode;
import com.gtercn.carhome.web.api.exception.ApiException;
import com.gtercn.carhome.web.api.form.SelfDrivingCommentForm;
import com.gtercn.carhome.web.api.form.SelfDrivingForm;
import com.gtercn.carhome.web.api.service.selfdriving.SelfDrivingTrowellingService;
import com.gtercn.carhome.web.api.utils.Encrypt;
import com.gtercn.carhome.web.api.view.JsonList;
import com.gtercn.carhome.web.api.view.JsonObjectView;

/**
 * 自驾游
 * 
 * @author Administrator
 * 
 * 2016-12-29 上午10:10:26
 */
@Controller
@RequestMapping({ "/app/v1/selfdriving" })
public class SelfdrivingController {
	@Autowired
	private SelfDrivingTrowellingService selfDrivingTrowellingService;
	
	/**
	 * 自驾游列表
	 */
	@ResponseBody
	@RequestMapping(value = "/open/list", method = RequestMethod.POST)
	public JsonList<SelfDrivingTrowelling> infoList(@RequestBody SelfDrivingForm form, HttpServletRequest request) throws ApiException {

		JsonList<SelfDrivingTrowelling> jsonList = new JsonList<SelfDrivingTrowelling>();
		List<SelfDrivingTrowelling> list = new ArrayList<SelfDrivingTrowelling>();
		
		// 城市编号
		String cityCode = form.getCityCode();
				
		// 获得token
		String token =request.getParameter("token");
		String userId = "";
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		}
		
		// 获取自驾游信息列表
		list = selfDrivingTrowellingService.getInfoList(userId, cityCode);
		
		// 检索结果返回
		if (list.size() > 0) {
			jsonList.setResult(list);
			jsonList.setMessage("查询自驾游成功");
		} else {
			throw new ApiException(ErrorCode.SELF_LIST_EMPTY_ERROR);
		}
		
		return jsonList;
	}
	
	/**
	 * 自驾游详情
	 */
	@ResponseBody
	@RequestMapping(value = "/open/searchone", method = RequestMethod.POST)
	public JsonObjectView searchOne(@RequestBody SelfDrivingForm form, HttpServletRequest request) throws ApiException {

		JsonObjectView jsonObjectView = new JsonObjectView();
		SelfDrivingTrowelling selfDrivingTrowelling = new SelfDrivingTrowelling();
		
		// 自驾游id
		String id = form.getId();
				
		// 获得token
		String token =request.getParameter("token");
		String userId = "";
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		}
		
		// 获取自驾游信息列表
		selfDrivingTrowelling = selfDrivingTrowellingService.getInfoOne(userId, id);
		
		// 检索结果返回
		if (selfDrivingTrowelling != null) {
			jsonObjectView.setResult(selfDrivingTrowelling);
			jsonObjectView.setMessage("查询自驾游成功");
		} else {
			throw new ApiException(ErrorCode.SELF_LIST_EMPTY_ERROR);
		}
		
		return jsonObjectView;
	}
	
	/**
	 * 自驾游评论
	 */
	@ResponseBody
	@RequestMapping(value = "/open/comment/list", method = RequestMethod.POST)
	public JsonList<Reply> commentList(@RequestBody SelfDrivingCommentForm form, HttpServletRequest request) throws ApiException {

		JsonList<Reply> jsonList = new JsonList<Reply>();
		List<Reply> list = new ArrayList<Reply>();
		
		// 获取自驾游Id
		String selfDrivingId = form.getSelfDrivingId();
		
		// 获取自驾游信息列表
		list = selfDrivingTrowellingService.getCommentList(selfDrivingId);
		
		// 检索结果返回
		if (list.size() > 0) {
			jsonList.setResult(list);
			jsonList.setMessage("查询自驾游评论成功");
		} else {
			throw new ApiException(ErrorCode.COMMENT_LIST_EMPTY_ERROR);
		}
		
		return jsonList;
	}
	
	/**
	 * 自驾游评论回复
	 */
	@ResponseBody
	@RequestMapping(value = "/open/comment/reply/list", method = RequestMethod.POST)
	public JsonList<Reply> commentReplyList(@RequestBody SelfDrivingCommentForm form, HttpServletRequest request) throws ApiException {

		JsonList<Reply> jsonList = new JsonList<Reply>();
		List<Reply> list = new ArrayList<Reply>();
				
		// 获取自驾游Id
		String selfDrivingId = form.getSelfDrivingId();
		String itemId = form.getItemId();
		
		// 判断传入的值是否为空
		if (StringUtils.isBlank(selfDrivingId)) {
			throw new ApiException(ErrorCode.SELF_DRIVING_ID_EMPTY_ERROR);
		}
		if (StringUtils.isBlank(itemId)) {
			throw new ApiException(ErrorCode.ITEM_ID_EMPTY_ERROR);
		}
		
		// 获取自驾游信息列表
		list = selfDrivingTrowellingService.getCommentReply(selfDrivingId, itemId);
		
		// 检索结果返回
		if (list.size() > 0) {
			jsonList.setResult(list);
			jsonList.setMessage("查询自驾游评论回复成功");
		} else {
			throw new ApiException(ErrorCode.COMMENT_REPLY_EMPTY_ERROR);
		}
		
		return jsonList;
	}
	
	/**
	 * 自驾游评论回复
	 */
	@ResponseBody
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public JsonList<Reply> comment(@RequestBody SelfDrivingCommentForm form, HttpServletRequest request) throws ApiException {

		JsonList<Reply> jsonList = new JsonList<Reply>();
		
		// 获得token
		String token =request.getParameter("token");
		String userId = "";
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		} else {
			throw new ApiException(ErrorCode.NOT_LOGIN_ERROR);
		}
				
		// 获取自驾游Id
		String selfDrivingId = form.getSelfDrivingId();
		String content = form.getContent();
		
		// 判断传入的值是否为空
		if (StringUtils.isBlank(selfDrivingId)) {
			throw new ApiException(ErrorCode.SELF_DRIVING_ID_EMPTY_ERROR);
		}
		if (StringUtils.isBlank(content)) {
			throw new ApiException(ErrorCode.CONTENT_EMPTY_ERROR);
		}
		
		// 获取自驾游信息列表
		selfDrivingTrowellingService.getComment(userId, selfDrivingId, content);
		
		jsonList.setMessage("自驾游评论成功");
		return jsonList;
	}
	
	/**
	 * 自驾游子评论回复
	 */
	@ResponseBody
	@RequestMapping(value = "/comment/reply", method = RequestMethod.POST)
	public JsonList<Reply> commentReply(@RequestBody SelfDrivingCommentForm form, HttpServletRequest request) throws ApiException {

		JsonList<Reply> jsonList = new JsonList<Reply>();
		
		// 获得token
		String token =request.getParameter("token");
		String userId = "";
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		} else {
			throw new ApiException(ErrorCode.NOT_LOGIN_ERROR);
		}
				
		// 获取自驾游Id
		String selfDrivingId = form.getSelfDrivingId();
		String content = form.getContent();
		String toUserId = form.getToUserId();
		String itemId = form.getItemId();
		
		// 判断传入的值是否为空
		if (StringUtils.isBlank(selfDrivingId)) {
			throw new ApiException(ErrorCode.SELF_DRIVING_ID_EMPTY_ERROR);
		}
		if (StringUtils.isBlank(content)) {
			throw new ApiException(ErrorCode.CONTENT_EMPTY_ERROR);
		}
		if (StringUtils.isBlank(toUserId)) {
			throw new ApiException(ErrorCode.TO_USERID_EMPTY_ERROR);
		}
		if (StringUtils.isBlank(itemId)) {
			throw new ApiException(ErrorCode.ITEM_ID_EMPTY_ERROR);
		}
		
		// 获取自驾游信息列表
		selfDrivingTrowellingService.getCommentSon(userId, selfDrivingId, content, toUserId, itemId);
		
		jsonList.setMessage("自驾游子评论成功");
		return jsonList;
	}
	
	/**
	 * 发布自驾游
	 */
	@ResponseBody
	@RequestMapping(value = "/release", method = RequestMethod.POST)
	public JsonObjectView release(@RequestParam("title") String title,
			@RequestParam("content") String content, @RequestParam("city_code") String cityCode,
			@RequestParam("activity_time") String activityTime, HttpServletRequest request) throws ApiException {

		// 图片
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		List<MultipartFile> list = new ArrayList<MultipartFile>();
		
		Map<String, MultipartFile> map = multipartRequest.getFileMap();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next().toString(); 
			list.add(map.get(key));  
		}
		
		JsonObjectView jsonObjectView = new JsonObjectView();
		
		// 获得token
		String token =request.getParameter("token");
		String userId = "";
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		} else {
			throw new ApiException(ErrorCode.NOT_LOGIN_ERROR);
		}
		if (activityTime == null || StringUtils.isBlank(activityTime)) {
			throw new ApiException(ErrorCode.ACTIVITY_TIME_EMPTY_ERROR);
		}
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("title", title);
		map1.put("content", content);
		map1.put("userId", userId);
		map1.put("cityCode", cityCode);
		map1.put("activityTime", activityTime);
		
		// 获取自驾游信息列表
		SelfDrivingTrowelling selfDrivingTrowelling = selfDrivingTrowellingService.getRelease(list, map1);
		jsonObjectView.setResult(selfDrivingTrowelling);
		jsonObjectView.setMessage("发布成功");
		
		return jsonObjectView;
	}
	
	/**
	 * 自驾游报名
	 */
	@ResponseBody
	@RequestMapping(value = "/enrol", method = RequestMethod.POST)
	public JsonObjectView enrol(@RequestBody SelfDrivingCommentForm form, HttpServletRequest request) throws ApiException {

		JsonObjectView jsonObjectView = new JsonObjectView();
		
		// 获得token
		String token =request.getParameter("token");
		String userId = "";
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		} else {
			throw new ApiException(ErrorCode.NOT_LOGIN_ERROR);
		}
				
		// 获取自驾游Id
		String selfDrivingId = form.getSelfDrivingId();
		
		SelfDrivingExtend entity = new SelfDrivingExtend();
		entity.setSelfDrivingId(selfDrivingId);
		entity.setUserId(userId);
		
		// 自驾游报名
		try {
			int back = selfDrivingTrowellingService.addEnrolData(entity);
			jsonObjectView.setResult(back);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObjectView.setMessage("自驾游报名成功");
		return jsonObjectView;
	}
	
	/**
	 * 自驾游报名解除
	 */
	@ResponseBody
	@RequestMapping(value = "/remove/enrol", method = RequestMethod.POST)
	public JsonObjectView removeEnrol(@RequestBody SelfDrivingCommentForm form, HttpServletRequest request) throws ApiException {

		JsonObjectView jsonObjectView = new JsonObjectView();
		
		// 获得token
		String token =request.getParameter("token");
		String userId = "";
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		} else {
			throw new ApiException(ErrorCode.NOT_LOGIN_ERROR);
		}
				
		// 获取自驾游Id
		String selfDrivingId = form.getSelfDrivingId();
		
		// 自驾游报名解除
		try {
			int back = selfDrivingTrowellingService.removeEnrolData(selfDrivingId, userId);
			jsonObjectView.setResult(back);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		jsonObjectView.setMessage("自驾游报名解除");
		return jsonObjectView;
	}
	
	/**
	 * 自驾游取消
	 */
	@ResponseBody
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public JsonObjectView cancel(@RequestBody SelfDrivingCommentForm form, HttpServletRequest request) throws ApiException {

		JsonObjectView jsonObjectView = new JsonObjectView();
		
		// 获得token
		String token =request.getParameter("token");
		String userId = "";
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		} else {
			throw new ApiException(ErrorCode.NOT_LOGIN_ERROR);
		}
				
		// 获取自驾游Id
		String selfDrivingId = form.getSelfDrivingId();
		
		// 自驾游取消
		try {
			int back = selfDrivingTrowellingService.cancel(selfDrivingId, userId);
			jsonObjectView.setResult(back);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		jsonObjectView.setMessage("自驾游取消");
		return jsonObjectView;
	}
	
	/**
	 * 我的活动（自驾游）
	 */
	@ResponseBody
	@RequestMapping(value = "/activity/list", method = RequestMethod.POST)
	public JsonList<SelfDrivingTrowelling> activityList(@RequestBody SelfDrivingForm form, HttpServletRequest request) throws ApiException {

		JsonList<SelfDrivingTrowelling> jsonList = new JsonList<SelfDrivingTrowelling>();
		
		// 获得token
		String token =request.getParameter("token");
		String userId = "";
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		} else {
			throw new ApiException(ErrorCode.NOT_LOGIN_ERROR);
		}
		// 城市编号
		String cityCode = form.getCityCode();		
		
		// 我的活动（自驾游）
		List<SelfDrivingTrowelling> list = selfDrivingTrowellingService.getActivityList(userId, cityCode);

		// 检索结果返回
		if (list.size() > 0) {
			jsonList.setResult(list);
			jsonList.setMessage("查询我的活动成功");
		} else {
			throw new ApiException(ErrorCode.SELF_LIST_EMPTY_ERROR);
		}
		
		jsonList.setMessage("我的活动(自驾游)");
		return jsonList;
	}
	
	/**
	 * 查看名单
	 */
	@ResponseBody
	@RequestMapping(value = "/searchname", method = RequestMethod.POST)
	public JsonList<Object> searchName(@RequestBody SelfDrivingCommentForm form, HttpServletRequest request) throws ApiException {

		JsonList<Object> jsonList = new JsonList<Object>();
		
		// 获得token
		String token =request.getParameter("token");
		if (StringUtils.isBlank(token)) {
			throw new ApiException(ErrorCode.NOT_LOGIN_ERROR);
		}
		// 自驾游ID
		String selfDrivingId = form.getSelfDrivingId();		
		
		// 我的活动（自驾游）
		List<Object> list = selfDrivingTrowellingService.getSearchName(selfDrivingId);

		// 检索结果返回
		if (list.size() > 0) {
			jsonList.setResult(list);
			jsonList.setMessage("查询查看名单");
		} else {
			throw new ApiException(ErrorCode.SEARCH_NAME_EMPTY_ERROR);
		}
		
		jsonList.setMessage("查看名单");
		return jsonList;
	}
}
