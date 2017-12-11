package com.gtercn.carhome.web.api.controller.rescue;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtercn.carhome.web.api.entity.Rescue;
import com.gtercn.carhome.web.api.entity.RescueDetail;
import com.gtercn.carhome.web.api.entity.RescueType;
import com.gtercn.carhome.web.api.entity.ServiceDetail;
import com.gtercn.carhome.web.api.entity.ServiceType;
import com.gtercn.carhome.web.api.enums.ErrorCode;
import com.gtercn.carhome.web.api.exception.ApiException;
import com.gtercn.carhome.web.api.form.RescueForm;
import com.gtercn.carhome.web.api.service.rescue.IRescueService;
import com.gtercn.carhome.web.api.utils.Encrypt;
import com.gtercn.carhome.web.api.view.JsonList;
import com.gtercn.carhome.web.api.view.JsonObjectView;

/**
 * 紧急救援
 * 
 * @author Administrator
 * 
 * 2016-11-29 上午15:10:26
 */
@Controller
@RequestMapping({ "/app/v1/open/service" })
public class RescueController {
	@Autowired
	private IRescueService rescueService;
	
	/**
	 * 紧急救援
	 */
	@ResponseBody
	@RequestMapping(value = "/rescuelist", method = RequestMethod.POST)
	public JsonList<Rescue> infoList(@RequestBody RescueForm form,HttpServletRequest request) throws ApiException {
		JsonList<Rescue> jsonList = new JsonList<Rescue>();
		String longtitude = form.getLongtitude();// 经度
		String latitude = form.getLatitude();// 纬度
		String token =request.getParameter("token");
		String userId = null;
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		}
		// 获取按类型下拉菜单
		List<String> mapType = this.rescueService.getTypeList();
		// 按距离下拉菜单
		List<String> distanceList = new ArrayList<String>();
		distanceList.add("附近");
		distanceList.add("1");
		distanceList.add("3");
		distanceList.add("5");
		distanceList.add("10");
		// 获取救援信息列表
		List<Rescue> list = this.rescueService.getInfoList(longtitude, latitude, userId);
		// 检索结果返回
		if (list.size() > 0) {
			list.get(0).setTypeList(mapType);
			list.get(0).setDistanceList(distanceList);
			jsonList.setResult(list);
			jsonList.setMessage("查询救援公司成功");
		} else {
			throw new ApiException(ErrorCode.RESCUE_LIST_EMPTY_ERROR);
		}
		return jsonList;
	}
	
	/**
	 * 紧急救援检索
	 */
	@ResponseBody
	@RequestMapping(value = "/search/rescuelist", method = RequestMethod.POST)
	public JsonList<Rescue> infoSearchList(@RequestBody RescueForm form,HttpServletRequest request) throws ApiException {
		JsonList<Rescue> jsonList = new JsonList<Rescue>();
		String longtitude = form.getLongtitude();// 经度
		String latitude = form.getLatitude();// 纬度
		String keyword = form.getKeyword();// 检索字段
		String token =request.getParameter("token");
		String userId = null;
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		}
		// 获取按类型下拉菜单
		List<String> mapType = this.rescueService.getTypeList();
		// 按距离下拉菜单
		List<String> distanceList = new ArrayList<String>();
		distanceList.add("附近");
		distanceList.add("1");
		distanceList.add("3");
		distanceList.add("5");
		distanceList.add("10");
		// 获取救援信息列表
		List<Rescue> list = this.rescueService.getInfoSearchList(longtitude, latitude, userId, keyword);
		// 检索结果返回
		if (list.size() > 0) {
			list.get(0).setTypeList(mapType);
			list.get(0).setDistanceList(distanceList);
			jsonList.setResult(list);
			jsonList.setMessage("查询救援公司成功");
		} else {
			throw new ApiException(ErrorCode.RESCUE_LIST_EMPTY_ERROR);
		}
		return jsonList;
	}
	
	/**
	 * 紧急救援（单个公司信息）
	 */
	@ResponseBody
	@RequestMapping(value = "/rescueOneDetail", method = RequestMethod.POST)
	public JsonObjectView rescueOneDetail(@RequestBody RescueForm form,HttpServletRequest request) throws ApiException {

		JsonObjectView jsonObjectView = new JsonObjectView();
		
		// 公司ID
		String shopId = form.getShopId();
		
		String token =request.getParameter("token");
		String userId = null;
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		}
		
		// 获取救援信息列表
		Rescue rescue = this.rescueService.getOneRescueInfo(shopId, userId);
		
		// 检索结果返回
		if (rescue != null) {
			jsonObjectView.setResult(rescue);
			jsonObjectView.setMessage("查询救援公司成功");
		} else {
			throw new ApiException(ErrorCode.RESCUE_EMPTY_ERROR);
		}
		
		return jsonObjectView;
	}
	
	/**
	 * 四类服务接口
	 */
	@ResponseBody
	@RequestMapping(value = "/servicetype/list", method = RequestMethod.POST)
	public JsonList<ServiceType> serviceTypeList(@RequestBody RescueForm form,HttpServletRequest request) throws ApiException {

		JsonList<ServiceType> jsonList = new JsonList<ServiceType>();
		List<ServiceType> list = new ArrayList<ServiceType>();
		
		// 城市编码
		String cityCode = form.getCityCode();
		//开始条数
		String beginNum = form.getBeginNumber();
		// 结束条数
		String overNum = form.getOverNumber();
		
		// 获取洗车信息列表
		list = this.rescueService.getServiceTypeList(cityCode, Integer.valueOf(beginNum), Integer.valueOf(overNum));
		
		// 检索结果返回
		if (list.size() > 0) {
			jsonList.setResult(list);
			jsonList.setMessage("查询四类服务公司成功");
		} else {
			throw new ApiException(ErrorCode.SHOP_EMPTY_ERROR);
		}
		
		return jsonList;
	}
	
	/**
	 * 四类服务详情接口
	 */
	@ResponseBody
	@RequestMapping(value = "/servicetype/detail", method = RequestMethod.POST)
	public JsonObjectView serviceTypeDetail(@RequestBody RescueForm form,HttpServletRequest request) throws ApiException {

		JsonObjectView jsonObjectView = new JsonObjectView();
		ServiceDetail serviceDetail = new ServiceDetail();
		
		// 店铺id
		String shopId = form.getShopId();
		
		String token =request.getParameter("token");
		String userId = null;
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		}
		
		// 获取洗车信息列表
		serviceDetail = this.rescueService.getServiceTypeDetail(shopId, userId);
		
		// 检索结果返回
		if (serviceDetail != null) {
			jsonObjectView.setResult(serviceDetail);
			jsonObjectView.setMessage("查询服务店铺成功");
		} else {
			throw new ApiException(ErrorCode.SHOP_EMPTY_ERROR);
		}
		
		return jsonObjectView;
	}
	
	/**
	 * 新救援接口
	 */
	@ResponseBody
	@RequestMapping(value = "/rescue/list", method = RequestMethod.POST)
	public JsonList<RescueType> rescueList(@RequestBody RescueForm form,HttpServletRequest request) throws ApiException {

		JsonList<RescueType> jsonList = new JsonList<RescueType>();
		List<RescueType> list = new ArrayList<RescueType>();
		
		// 城市编码
		String cityCode = form.getCityCode();
		//开始条数
		String beginNum = form.getBeginNumber();
		// 结束条数
		String overNum = form.getOverNumber();
		
		// 获取洗车信息列表
		list = this.rescueService.getRescueList(cityCode, Integer.valueOf(beginNum), Integer.valueOf(overNum));
		
		// 检索结果返回
		if (list.size() > 0) {
			jsonList.setResult(list);
			jsonList.setMessage("查询救援服务公司成功");
		} else {
			throw new ApiException(ErrorCode.RESCUE_LIST_EMPTY_ERROR);
		}
		
		return jsonList;
	}
	
	/**
	 * 新救援详情接口
	 */
	@ResponseBody
	@RequestMapping(value = "/rescuetype/detail", method = RequestMethod.POST)
	public JsonObjectView rescueTypeDetail(@RequestBody RescueForm form,HttpServletRequest request) throws ApiException {

		JsonObjectView jsonObjectView = new JsonObjectView();
		RescueDetail rescueDetail = new RescueDetail();
		
		// 店铺id
		String rescueId = form.getRescueId();
		
		String token =request.getParameter("token");
		String userId = null;
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		}
		
		// 获取洗车信息列表
		rescueDetail = this.rescueService.getRescueTypeDetail(rescueId, userId);
		
		// 检索结果返回
		if (rescueDetail != null) {
			jsonObjectView.setResult(rescueDetail);
			jsonObjectView.setMessage("查询救援服务店铺成功");
		} else {
			throw new ApiException(ErrorCode.SHOP_EMPTY_ERROR);
		}
		
		return jsonObjectView;
	}
	
	/**
	 * 洗车接口
	 */
	@ResponseBody
	@RequestMapping(value = "/cleaning/list", method = RequestMethod.POST)
	public JsonList<Rescue> cleaningList(@RequestBody RescueForm form,HttpServletRequest request) throws ApiException {

		JsonList<Rescue> jsonList = new JsonList<Rescue>();
		List<Rescue> list = new ArrayList<Rescue>();
		
		// 经度
		String longtitude = form.getLongtitude();
		// 纬度
		String latitude = form.getLatitude();
		
		// 获取地区下拉菜单
		List<String> mapType = this.rescueService.getDistrictList();
		
		String token =request.getParameter("token");
		String userId = null;
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		}
		
		// 获取洗车信息列表
		list = this.rescueService.getServiceList(longtitude, latitude, "4100", userId);
		
		// 检索结果返回
		if (list.size() > 0) {
			list.get(0).setDistrictList(mapType);
			jsonList.setResult(list);
			jsonList.setMessage("查询洗车公司成功");
		} else {
			throw new ApiException(ErrorCode.CLEANING_LIST_EMPTY_ERROR);
		}
		
		return jsonList;
	}
	
	/**
	 * 洗车检索接口
	 */
	@ResponseBody
	@RequestMapping(value = "/cleaning/search/list", method = RequestMethod.POST)
	public JsonList<Rescue> cleaningSearchList(@RequestBody RescueForm form,HttpServletRequest request) throws ApiException {

		JsonList<Rescue> jsonList = new JsonList<Rescue>();
		List<Rescue> list = new ArrayList<Rescue>();
		
		// 经度
		String longtitude = form.getLongtitude();
		// 纬度
		String latitude = form.getLatitude();
		// 检索字段
		String keyword = form.getKeyword();
		
		// 获取地区下拉菜单
		List<String> mapType = this.rescueService.getDistrictList();
		
		String token =request.getParameter("token");
		String userId = null;
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		}
		
		// 获取洗车信息列表
		list = this.rescueService.getServiceSearchList(longtitude, latitude, "4100", userId, keyword);
		
		// 检索结果返回
		if (list.size() > 0) {
			list.get(0).setDistrictList(mapType);
			jsonList.setResult(list);
			jsonList.setMessage("查询洗车公司成功");
		} else {
			throw new ApiException(ErrorCode.CLEANING_LIST_EMPTY_ERROR);
		}
		
		return jsonList;
	}
	
	/**
	 * 修车接口
	 */
	@ResponseBody
	@RequestMapping(value = "/repair/list", method = RequestMethod.POST)
	public JsonList<Rescue> repairList(@RequestBody RescueForm form,HttpServletRequest request) throws ApiException {

		JsonList<Rescue> jsonList = new JsonList<Rescue>();
		List<Rescue> list = new ArrayList<Rescue>();
		
		// 经度
		String longtitude = form.getLongtitude();
		// 纬度
		String latitude = form.getLatitude();
		
		// 获取地区下拉菜单
		List<String> mapType = this.rescueService.getDistrictList();
		
		String token =request.getParameter("token");
		String userId = null;
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		}
		
		// 获取修车信息列表
		list = this.rescueService.getServiceList(longtitude, latitude, "5100", userId);
		
		// 检索结果返回
		if (list.size() > 0) {
			list.get(0).setDistrictList(mapType);
			jsonList.setResult(list);
			jsonList.setMessage("查询修车公司成功");
		} else {
			throw new ApiException(ErrorCode.REPAIR_LIST_EMPTY_ERROR);
		}
		
		return jsonList;
	}
	
	/**
	 * 修车检索接口
	 */
	@ResponseBody
	@RequestMapping(value = "/repair/search/list", method = RequestMethod.POST)
	public JsonList<Rescue> repairSearchList(@RequestBody RescueForm form,HttpServletRequest request) throws ApiException {

		JsonList<Rescue> jsonList = new JsonList<Rescue>();
		List<Rescue> list = new ArrayList<Rescue>();
		
		// 经度
		String longtitude = form.getLongtitude();
		// 纬度
		String latitude = form.getLatitude();
		// 检索字段
		String keyword = form.getKeyword();
		
		// 获取地区下拉菜单
		List<String> mapType = this.rescueService.getDistrictList();
		
		String token =request.getParameter("token");
		String userId = null;
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		}
		
		// 获取修车信息列表
		list = this.rescueService.getServiceSearchList(longtitude, latitude, "5100", userId, keyword);
		
		// 检索结果返回
		if (list.size() > 0) {
			list.get(0).setDistrictList(mapType);
			jsonList.setResult(list);
			jsonList.setMessage("查询修车公司成功");
		} else {
			throw new ApiException(ErrorCode.REPAIR_LIST_EMPTY_ERROR);
		}
		
		return jsonList;
	}
	
	/**
	 * 汽车保养接口
	 */
	@ResponseBody
	@RequestMapping(value = "/maintain/list", method = RequestMethod.POST)
	public JsonList<Rescue> maintainList(@RequestBody RescueForm form,HttpServletRequest request) throws ApiException {

		JsonList<Rescue> jsonList = new JsonList<Rescue>();
		List<Rescue> list = new ArrayList<Rescue>();
		
		// 经度
		String longtitude = form.getLongtitude();
		// 纬度
		String latitude = form.getLatitude();
		
		// 获取地区下拉菜单
		List<String> mapType = this.rescueService.getDistrictList();
		
		String token =request.getParameter("token");
		String userId = null;
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		}
		
		// 获取汽车保养信息列表
		list = this.rescueService.getServiceList(longtitude, latitude, "6100", userId);
		
		// 检索结果返回
		if (list.size() > 0) {
			list.get(0).setDistrictList(mapType);
			jsonList.setResult(list);
			jsonList.setMessage("查询汽车保养公司成功");
		} else {
			throw new ApiException(ErrorCode.MAINTAIN_LIST_EMPTY_ERROR);
		}
		
		return jsonList;
	}
	
	/**
	 * 汽车保养查询接口
	 */
	@ResponseBody
	@RequestMapping(value = "/maintain/search/list", method = RequestMethod.POST)
	public JsonList<Rescue> maintainSearchList(@RequestBody RescueForm form,HttpServletRequest request) throws ApiException {

		JsonList<Rescue> jsonList = new JsonList<Rescue>();
		List<Rescue> list = new ArrayList<Rescue>();
		
		// 经度
		String longtitude = form.getLongtitude();
		// 纬度
		String latitude = form.getLatitude();
		// 检索字段
		String keyword = form.getKeyword();
		
		// 获取地区下拉菜单
		List<String> mapType = this.rescueService.getDistrictList();
		
		String token =request.getParameter("token");
		String userId = null;
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		}
		
		// 获取汽车保养信息列表
		list = this.rescueService.getServiceSearchList(longtitude, latitude, "6100", userId, keyword);
		
		// 检索结果返回
		if (list.size() > 0) {
			list.get(0).setDistrictList(mapType);
			jsonList.setResult(list);
			jsonList.setMessage("查询汽车保养公司成功");
		} else {
			throw new ApiException(ErrorCode.MAINTAIN_LIST_EMPTY_ERROR);
		}
		
		return jsonList;
	}
	
	/**
	 * 轮胎接口
	 */
	@ResponseBody
	@RequestMapping(value = "/tyre/list", method = RequestMethod.POST)
	public JsonList<Rescue> tyreList(@RequestBody RescueForm form,HttpServletRequest request) throws ApiException {

		JsonList<Rescue> jsonList = new JsonList<Rescue>();
		List<Rescue> list = new ArrayList<Rescue>();
		
		// 经度
		String longtitude = form.getLongtitude();
		// 纬度
		String latitude = form.getLatitude();
		
		String token =request.getParameter("token");
		String userId = null;
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		}
		
		// 获取地区下拉菜单
		List<String> mapType = this.rescueService.getDistrictList();
		
		// 获取汽车保养信息列表
		list = this.rescueService.getServiceList(longtitude, latitude, "7100", userId);
		
		// 检索结果返回
		if (list.size() > 0) {
			list.get(0).setDistrictList(mapType);
			jsonList.setResult(list);
			jsonList.setMessage("查询更换轮胎公司成功");
		} else {
			throw new ApiException(ErrorCode.TYRE_LIST_EMPTY_ERROR);
		}
		
		return jsonList;
	}
	
	/**
	 * 轮胎检索接口
	 */
	@ResponseBody
	@RequestMapping(value = "/tyre/search/list", method = RequestMethod.POST)
	public JsonList<Rescue> tyreSearchList(@RequestBody RescueForm form,HttpServletRequest request) throws ApiException {

		JsonList<Rescue> jsonList = new JsonList<Rescue>();
		List<Rescue> list = new ArrayList<Rescue>();
		
		// 经度
		String longtitude = form.getLongtitude();
		// 纬度
		String latitude = form.getLatitude();
		// 检索字段
		String keyword = form.getKeyword();
		
		String token =request.getParameter("token");
		String userId = null;
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		}
		
		// 获取地区下拉菜单
		List<String> mapType = this.rescueService.getDistrictList();
		
		// 获取汽车保养信息列表
		list = this.rescueService.getServiceSearchList(longtitude, latitude, "7100", userId, keyword);
		
		// 检索结果返回
		if (list.size() > 0) {
			list.get(0).setDistrictList(mapType);
			jsonList.setResult(list);
			jsonList.setMessage("查询更换轮胎公司成功");
		} else {
			throw new ApiException(ErrorCode.TYRE_LIST_EMPTY_ERROR);
		}
		
		return jsonList;
	}
	
	/**
	 * 查询商家服务信息
	 */
	@ResponseBody
	@RequestMapping(value = "/servicediftype", method = RequestMethod.POST)
	public JsonObjectView serviceDifType(@RequestBody RescueForm form,HttpServletRequest request) throws ApiException {

		JsonObjectView jsonObjectView = new JsonObjectView();
		
		// 经度
		String longtitude = form.getLongtitude();
		// 纬度
		String latitude = form.getLatitude();
		// 商家ID
		String shopId = form.getShopId();
		//服务类型
		String serviceType = form.getServiceType();
		
		String token =request.getParameter("token");
		String userId = null;
		if (StringUtils.isNotBlank(token)) {
			userId = Encrypt.getEncryptUserId(token);
		}
		
		// 获取汽车保养信息列表
		Rescue rescue = this.rescueService.getServiceDifType(longtitude, latitude, shopId, serviceType, userId);
		
		// 检索结果返回
		if (rescue != null) {
			jsonObjectView.setResult(rescue);
			jsonObjectView.setMessage("查询成功");
		} else {
			throw new ApiException(ErrorCode.TYRE_LIST_EMPTY_ERROR);
		}
		
		return jsonObjectView;
	}
}
