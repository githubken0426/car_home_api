package com.gtercn.carhome.web.api.controller.search;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtercn.carhome.web.api.entity.Rescue;
import com.gtercn.carhome.web.api.exception.ApiException;
import com.gtercn.carhome.web.api.form.RescueForm;
import com.gtercn.carhome.web.api.service.search.HomeSearchService;
import com.gtercn.carhome.web.api.utils.Encrypt;
import com.gtercn.carhome.web.api.view.JsonList;

/**
 * 搜索
 * 
 * @author ken
 * 2016-12-27 上午09:01:34
 */
@Controller
@RequestMapping(value="/app/v1/open/home")
public class SearchController {
	@Autowired
	private HomeSearchService homeSearchService;
	
	/**
	 * 搜索数据
	 * @param request
	 * @return
	 * 2016-12-27 上午09:01:34
	 * @throws ApiException 
	 */
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public JsonList<Rescue> getSearchData(@RequestBody RescueForm form,HttpServletRequest request) throws ApiException{
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		String shopName = form.getKeyword();
		String cityCode = form.getCityCode();
		String token =request.getParameter("token");
		String userId = null;
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		}
		
		map.put("userId", userId);
		map.put("shopName", shopName);
		map.put("cityCode", cityCode);
		
		JsonList<Rescue> view = new JsonList<Rescue>();
		List<Rescue> list= homeSearchService.getSearchData(map);
		if(list.size()==0){
			view.setMessage("没搜索到数据");
		}
		view.setResult(list);
		return view;
	}

}
