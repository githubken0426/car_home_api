package com.gtercn.carhome.web.api.controller.information;

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

import com.gtercn.carhome.web.api.entity.Information;
import com.gtercn.carhome.web.api.exception.ApiException;
import com.gtercn.carhome.web.api.form.IdForm;
import com.gtercn.carhome.web.api.service.information.InformationService;
import com.gtercn.carhome.web.api.utils.Encrypt;
import com.gtercn.carhome.web.api.view.JsonObjectView;

/**
 * 资讯
 * 
 * @author ken
 * 2016-12-27 上午09:01:34
 */
@Controller
@RequestMapping(value="/app/v1/open/information")
public class InformationController {
	@Autowired
	private InformationService informationService;
	
	/**
	 * 查询所有数据
	 * @param request
	 * @return
	 * 2016-12-27 上午09:01:34
	 * @throws ApiException 
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public JsonObjectView informationList(HttpServletRequest request) throws ApiException{
		Map<String,Object> map=new HashMap<String,Object>();
		JsonObjectView view=new JsonObjectView();
		String token =request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		map.put("userId", userId);
		List<Information> list= informationService.selectAllData(map);
		view.setResult(list);
		if(list.size()==0){
			view.setMessage("没有找到数据");
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public JsonObjectView informationSearchList(HttpServletRequest request) throws ApiException{
		Map<String,Object> map=new HashMap<String,Object>();
		JsonObjectView view=new JsonObjectView();
		String keyword=request.getParameter("keyword");
		String token =request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		map.put("userId",userId);
		map.put("title",keyword);
		List<Information> list= informationService.selectAllData(map);
		view.setResult(list);
		if(list.size()==0){
			view.setMessage("没有找到数据");
		}
		return view;
	}
	
	/**
	 * 资讯详情
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
		Information info=informationService.selectInformationDetail(map);
		view.setResult(info);
		return view;
	}
}
