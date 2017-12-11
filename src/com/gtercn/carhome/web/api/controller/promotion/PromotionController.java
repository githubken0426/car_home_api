package com.gtercn.carhome.web.api.controller.promotion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtercn.carhome.web.api.CarHomeApiProperties;
import com.gtercn.carhome.web.api.entity.Promotion;
import com.gtercn.carhome.web.api.exception.ApiException;
import com.gtercn.carhome.web.api.form.IdForm;
import com.gtercn.carhome.web.api.service.promotion.PromotionService;
import com.gtercn.carhome.web.api.utils.Encrypt;
import com.gtercn.carhome.web.api.view.JsonObjectView;

/**
 * 促销
 * 
 * @author ken
 * 2016-12-23 下午02:22:26
 */
@Controller
@RequestMapping(value="/app/v1/open/promotion")
public class PromotionController {
	@Autowired
	private PromotionService promotionService;
	
	/**
	 * 查询所有数据
	 * @param request
	 * @return
	 * 2016-12-20 上午11:45:40
	 * @throws ApiException 
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public JsonObjectView promotionList(HttpServletRequest request) throws ApiException{
		Map<String,Object> map=new HashMap<String,Object>();
		JsonObjectView view=new JsonObjectView();
		String token =request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		String cityCode =request.getParameter("city_code");
		if(cityCode==null || cityCode.equals(""))
			cityCode=CarHomeApiProperties.DEFAULT_CITY_CODE;
		map.put("userId",userId);
		map.put("cityCode",cityCode);
		List<Promotion> list= promotionService.selectAllData(map);
		view.setResult(list);
		if(list.size()==0){
			view.setMessage("没有找到数据");
		}
		return view;
	}
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public JsonObjectView promotionMapList(HttpServletRequest request) throws ApiException{
		Map<String,Object> map=new HashMap<String,Object>();
		JsonObjectView view=new JsonObjectView();
		String keyword=request.getParameter("keyword");
		String token =request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		String cityCode =request.getParameter("city_code");
		if(cityCode==null || cityCode.equals(""))
			cityCode=CarHomeApiProperties.DEFAULT_CITY_CODE;
		map.put("cityCode",cityCode);
		map.put("userId",userId);
		map.put("title",keyword);
		List<Promotion> list= promotionService.selectAllData(map);
		view.setResult(list);
		if(list.size()==0){
			view.setMessage("没有找到数据");
		}
		return view;
	}
	
	/**
	 * 详情
	 * @param id
	 * @param request
	 * @return
	 * @throws ApiException
	 * 2017-3-13 下午05:19:21
	 */
	@ResponseBody
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public JsonObjectView queryDetail(@RequestBody IdForm form,
			HttpServletRequest request)throws ApiException {
		JsonObjectView view = new JsonObjectView();
		Map<String,Object> map=new HashMap<String,Object>();
		String token =request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		map.put("userId", userId);
		String id=form.getId();
		map.put("id", id);
		Promotion promotion =promotionService.selectPromotionDetail(map);
		view.setResult(promotion);
		return view;
	}
}
