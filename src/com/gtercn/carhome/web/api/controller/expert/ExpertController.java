package com.gtercn.carhome.web.api.controller.expert;

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

import com.gtercn.carhome.web.api.entity.ExpertTop;
import com.gtercn.carhome.web.api.entity.ExpertType;
import com.gtercn.carhome.web.api.exception.ApiException;
import com.gtercn.carhome.web.api.form.ExpertForm;
import com.gtercn.carhome.web.api.service.experttop.ExpertTopService;
import com.gtercn.carhome.web.api.service.experttype.ExpertTypeService;
import com.gtercn.carhome.web.api.view.JsonList;

/**
 * 达人榜相关
 * 
 * @author ken
 * 2016-12-14 上午11:02:31
 */
@Controller
@RequestMapping("/app/v1/open/expert")
public class ExpertController {
	@Autowired
	private ExpertTypeService expertTypeService;
	@Autowired
	private ExpertTopService expertTopService;
	/**
	 * 查询所有达人类别
	 * @param request
	 * @return
	 * 2016-12-14 上午08:38:04
	 */
	@ResponseBody
	@RequestMapping(value = "/type", method = RequestMethod.POST)
	public JsonList<ExpertType> queryTypeData(HttpServletRequest request) throws ApiException{
		JsonList<ExpertType> jv = new JsonList<ExpertType>();
		List<ExpertType> typeList=expertTypeService.queryAllData();
		jv.setResult(typeList);
		return jv;
	}
	/**
	 * 查询所有达人
	 * @param request
	 * @return
	 * 2016-12-14 上午08:38:04
	 */
	@ResponseBody
	@RequestMapping(value = "/top", method = RequestMethod.POST)
	public JsonList<ExpertTop> queryTopData(@RequestBody ExpertForm form,
			HttpServletRequest request) throws ApiException{
		JsonList<ExpertTop> view = new JsonList<ExpertTop>();
		Map<String,Object> map=new HashMap<String,Object>();
		String type=form.getExpertType();
		map.put("type", type);
		map.put("cityCode", form.getCityCode());
		List<ExpertTop> topList=expertTopService.queryAllData(map);
		if(!(topList.size()>0)){
			view.setMessage("没有找到数据");
		}
		view.setResult(topList);
		return view;
	}

}
