package com.gtercn.carhome.web.api.controller.favor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtercn.carhome.web.api.entity.Favor;
import com.gtercn.carhome.web.api.enums.ErrorCode;
import com.gtercn.carhome.web.api.exception.ApiException;
import com.gtercn.carhome.web.api.form.FavorForm;
import com.gtercn.carhome.web.api.service.favor.FavorService;
import com.gtercn.carhome.web.api.utils.CommonUtil;
import com.gtercn.carhome.web.api.utils.Encrypt;
import com.gtercn.carhome.web.api.view.JsonObjectView;

@Controller
@RequestMapping(value="/app/v1/favor")
public class FavorController {
	@Autowired
	private FavorService favorService;
	
	/**
	 * 收藏
	 * @param request
	 * @return
	 * 2016-12-20 上午11:45:40
	 * @throws ApiException 
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonObjectView addFavor(@RequestBody FavorForm form,Favor favor,
			HttpServletRequest request) throws ApiException{
		JsonObjectView view=new JsonObjectView();
		if(!form.checkFavor()){
			throw new ApiException(ErrorCode.SYS_PARAM_NULL_ERROR);
		}
		String token =request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		String favorId=form.getFavorId();
		String favorType=form.getFavorType();
		favor.setId(CommonUtil.getUID());
		favor.setUserId(userId);
		favor.setFavorId(favorId);
		int type=(favorType!=null && favorType!="")?Integer.parseInt(favorType):null;
		favor.setFavorType(type);
		favorService.saveOrUpdate(favor);
		view.setMessage("收藏成功");
		return view;
	}
	
	/**
	 * 取消收藏
	 * @param request
	 * @return
	 * 2016-12-20 上午11:45:40
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JsonObjectView deleteFavor(@RequestBody FavorForm form,HttpServletRequest request)throws ApiException{
		JsonObjectView view=new JsonObjectView();
		Map<String,Object> map=new HashMap<String,Object>();
		if(!form.checkFavor()){
			throw new ApiException(ErrorCode.SYS_PARAM_NULL_ERROR);
		}
		String token =request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		String favorId=form.getFavorId();
		String favorType=form.getFavorType();
		map.put("favorId",favorId);
		map.put("userId",userId);
		map.put("favorType",favorType);
		map.put("deleteFlag",1);
		int result = favorService.cancelFavor(map);
		view.setMessage("取消收藏");
		if(result!=1){
			view.setErr_code("-1");
			view.setErr_message("error");
			view.setMessage("取消失败");
		}
		return view;
	}
}
