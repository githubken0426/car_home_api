package com.gtercn.carhome.web.api.controller.account;

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
import com.gtercn.carhome.web.api.entity.Dictionary;
import com.gtercn.carhome.web.api.entity.User;
import com.gtercn.carhome.web.api.entity.UserLoginlog;
import com.gtercn.carhome.web.api.entity.VerifyCode;
import com.gtercn.carhome.web.api.enums.ErrorCode;
import com.gtercn.carhome.web.api.exception.ApiException;
import com.gtercn.carhome.web.api.form.UserInfoForm;
import com.gtercn.carhome.web.api.form.UserLoginlogForm;
import com.gtercn.carhome.web.api.form.UserSecurityForm;
import com.gtercn.carhome.web.api.service.dictionary.DictionaryService;
import com.gtercn.carhome.web.api.service.loginlog.IUserLoginlogService;
import com.gtercn.carhome.web.api.service.user.IUserService;
import com.gtercn.carhome.web.api.service.verifycode.IVerifyCodeService;
import com.gtercn.carhome.web.api.utils.AliSMSUtil;
import com.gtercn.carhome.web.api.utils.CommonUtil;
import com.gtercn.carhome.web.api.utils.Encrypt;
import com.gtercn.carhome.web.api.view.JsonObjectView;
import com.gtercn.carhome.web.api.view.JsonView;

/**
 * 账号信息
 * 
 * @author Administrator
 * 
 *         2016-8-16 上午10:23:37
 */
@Controller
@RequestMapping(value = "/app/v1/account")
public class AccountController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserLoginlogService userLoginlogService;
	@Autowired
	private IVerifyCodeService verifyCodeService;
	@Autowired
	private DictionaryService dictionaryService;

	/**
	 * 用户名密码登陆
	 * 
	 * @param request
	 * @return // * @throws ApiException
	 */
	@ResponseBody
	@RequestMapping(value = "/open/login", method = RequestMethod.POST)
	public JsonView appLogin(@RequestBody UserInfoForm form,
			HttpServletRequest request) throws ApiException {
		JsonView jsonView = new JsonView();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		// 参数错误
		if (!form.checkLogin()) {
			throw new ApiException(ErrorCode.SYS_PARAM_NULL_ERROR);
		}
		if (!form.checkPhoneFormat()) {
			throw new ApiException(ErrorCode.USER_PHONE_FORMAT_ERROR);
		}
		String loginPhone = form.getLoginPhone();
		String pwd = CommonUtil.gernateToMD5(form.getPassword());
		String deviceType = form.getDeviceType();
		String deviceToken = form.getDeviceToken();
		// 用户不存在(未注册)
		map.put("loginPhone", loginPhone);
		int account = userService.getCountByAccount(map);
		if (!(account > 0)) {
			throw new ApiException(ErrorCode.USER_ACCOUNT_UNREGISTER);
		}
		// 登陆
		map.put("password", pwd);
		User user = userService.appLogin(map);
		if (null == user) {// 密码错误
			throw new ApiException(ErrorCode.USER_LOGIN_PASSWORD_ERROR);
		}
		/**
		 * 登陆成功
		 */
		// 记录登陆日志,更新用户设备token
		UserLoginlog loginLog = new UserLoginlog();
		loginLog.setLogId(CommonUtil.getUID());
		String userId = user.getUserId();
		form.setUserId(userId);// 传入切面AspectUserDevice的参数
		loginLog.setUserId(userId);
		// String token = CommonUtil.gernateToMD5(userId + "_" +
		// System.currentTimeMillis());
		String token = Encrypt
				.encode(userId + "_" + System.currentTimeMillis());// + "_"+
		// deviceToken
		loginLog.setToken(token);
		loginLog.setDeviceToken(deviceToken);
		loginLog.setDeviceType(Integer.valueOf(deviceType));
		loginLog.setDeleteFlag(0);

		map.put("deviceType", deviceType);
		map.put("deviceToken", deviceToken);
		map.put("userId", userId);
		userService.operationTokenAndLog(map, loginLog);
		resultMap.put("user_info", user);
		resultMap.put("token", token);
		// resultMap.put("message", "登陆成功");
		jsonView.setMessage(ErrorCode.USER_LOGIN_SUCCESS.getMessage());
		jsonView.setResult(resultMap);
		return jsonView;
	}

	/**
	 * 通过登陆日志登陆
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ApiException
	 */
	@ResponseBody
	@RequestMapping(value = "/open/info", method = RequestMethod.POST)
	public JsonView appUserInfo(@RequestBody UserLoginlogForm form,
			HttpServletRequest request) throws ApiException {
		JsonView jsonView = new JsonView();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String token = form.getToken();
		String userId = Encrypt.getEncryptUserId(token);
		form.setUserId(userId);
		if (!form.checkUserInfo()) {
			throw new ApiException(ErrorCode.SYS_PARAM_NULL_ERROR);
		}
		UserLoginlog loginInfo = userLoginlogService.getLastLogByUserId(userId);
		// token比对
		if (loginInfo == null || (loginInfo.getToken() == null)
				|| (!loginInfo.getToken().equals(token))) {
			throw new ApiException(ErrorCode.USER_LOGIN_OTHER_ERROR);
		}
		// 比对正确，登陆成功
		String logId = loginInfo.getLogId();
		// 更新登陆时间
		userLoginlogService.updateLoginTime(logId);
		// 获取用户信息
		User user = userService.getUserById(loginInfo.getUserId());
		resultMap.put("token", token);
		resultMap.put("user_Info", user);
		jsonView.setMessage(ErrorCode.USER_LOGIN_SUCCESS.getMessage());
		jsonView.setResult(resultMap);
		return jsonView;
	}

	/**
	 * 注册
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@ResponseBody
	@RequestMapping(value = "/open/register", method = RequestMethod.POST)
	public JsonView appRegister(@RequestBody UserInfoForm form,
			HttpServletRequest request) throws ApiException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JsonView jsonView = new JsonView();
		if (!form.checkRegister()) {
			throw new ApiException(ErrorCode.SYS_PARAM_NULL_ERROR);
		}
		if (!form.checkPhoneFormat()) {
			throw new ApiException(ErrorCode.USER_PHONE_FORMAT_ERROR);
		}
		String loginPhone = form.getLoginPhone();
		String deviceToken = form.getDeviceToken();
		String verifyCode = form.getVerifyCode();
		String verifyType = form.getVerifyType();
		// 数据库查询出验证码进行比对
		boolean isRight = validateVerifyCode(loginPhone, verifyCode, verifyType);
		// 验证码不一致
		if (!isRight) {
			throw new ApiException(ErrorCode.USER_VERIFY_ERROR);
		}
		// 1: 用户信息
		User user = new User();
		String userId = CommonUtil.getUID();
		// String token = CommonUtil.gernateToMD5(userId + "_" +
		// System.currentTimeMillis());
		String token = Encrypt.encode(userId + "_" + System.currentTimeMillis()
				+ "_" + deviceToken);
		Integer type = Integer.parseInt(form.getDeviceType());
		String pwd = CommonUtil.gernateToMD5(form.getPassword());
		user.setUserId(userId);
		user.setDeviceToken(deviceToken);
		user.setDeviceType(type);
		user.setLoginPhone(loginPhone);
		user.setPassword(pwd);
		user.setDeleteFlag(0);
		// 2: 用户积分明细
		// Integral integral = new Integral();
		// integral.setIntegralId(CommonUtil.getUID());
		// integral.setUserId(userId);
		// // 注册送积分,数字字典查询
		// Dictionary dicrionary=dictionaryService.selectByPrimaryKey("2100");
		// Integer intergral = (dicrionary==null) ?
		// 0:Integer.valueOf(dicrionary.getDictionaryValue());
		// integral.setIntegral(intergral);
		// integral.setDeleteFlag(0);
		// integral.setGainWay("3");// 获取积分方式 3注册
		// // 3： 用户扩展
		// UserExtend extend = new UserExtend();
		// extend.setUserId(userId);
		// extend.setIntegral(intergral);
		// extend.setDeleteFlag(0);
		// 4.记录登陆日志
		UserLoginlog loginLog = new UserLoginlog();
		loginLog.setLogId(CommonUtil.getUID());
		loginLog.setUserId(userId);
		loginLog.setToken(token);
		loginLog.setDeviceToken(deviceToken);
		loginLog.setDeviceType(Integer.valueOf(type));
		loginLog.setDeleteFlag(0);
		// 插入数据
		userService.userRegister(user, loginLog);
		jsonView.setMessage("欢迎加入顺驾天下！");
		resultMap.put("user_info", user);
		resultMap.put("token", token);
		jsonView.setResult(resultMap);
		return jsonView;
	}

	/**
	 * 忘记密码接口
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@ResponseBody
	@RequestMapping(value = "/open/password/forget", method = RequestMethod.POST)
	public JsonView appPwdForgert(@RequestBody UserInfoForm form,
			HttpServletRequest request) throws ApiException {
		Map<String, Object> map = new HashMap<String, Object>();
		JsonView jsonView = new JsonView();
		if (!form.checkPwdForget()) {
			throw new ApiException(ErrorCode.SYS_PARAM_NULL_ERROR);
		}
		if (!form.checkPhoneFormat()) {
			throw new ApiException(ErrorCode.USER_PHONE_FORMAT_ERROR);
		}
		String loginPhone = form.getLoginPhone();
		String verifyCode = form.getVerifyCode();
		String verifyType = form.getVerifyType();
		// 数据库查询出验证码进行比对
		boolean isRight = validateVerifyCode(loginPhone, verifyCode, verifyType);
		// 验证码不一致
		if (!isRight) {
			throw new ApiException(ErrorCode.USER_VERIFY_ERROR);
		}
		map.put("loginPhone", loginPhone);
		List<User> account = userService.getUserByAccount(map);
		map.put("userId", account.get(0).getUserId());
		String password = CommonUtil.gernateToMD5(form.getPassword());
		map.put("newPwd", password);
		userService.resetPassword(map);
		jsonView.setMessage("密码重置成功");
		return jsonView;
	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@ResponseBody
	@RequestMapping(value = "/open/logout", method = RequestMethod.POST)
	public JsonView appLogout(@RequestBody UserInfoForm form,
			HttpServletRequest request) throws ApiException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JsonView jsonView = new JsonView();
		String token = form.getToken();
		String userId = Encrypt.getEncryptUserId(token);
		if (userId == "" || userId == null) {
			throw new ApiException(ErrorCode.SYS_PARAM_NULL_ERROR);
		}
		userLoginlogService.updateLastLogbyUserId(userId);
		jsonView.setResult(resultMap);
		jsonView.setMessage("安全退出");
		return jsonView;
	}

	/**
	 * 修改密码接口
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@Deprecated
	@ResponseBody
	@RequestMapping(value = "/password/change", method = RequestMethod.POST)
	public JsonView appPwdChange(@RequestBody UserInfoForm form,
			HttpServletRequest request) throws ApiException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		JsonView jsonView = new JsonView();
		if (!form.checkPwdChange()) {
			throw new ApiException(ErrorCode.SYS_PARAM_NULL_ERROR);
		}
		if (!form.checkPhoneFormat()) {
			throw new ApiException(ErrorCode.USER_PHONE_FORMAT_ERROR);
		}
		String loginPhone = form.getLoginPhone();
		String oldPwd = CommonUtil.gernateToMD5(form.getOldPwd());
		String newPwd = CommonUtil.gernateToMD5(form.getNewPwd());
		map.put("loginPhone", loginPhone);
		map.put("password", oldPwd);
		// 判断原密码是否正确
		User user = userService.appLogin(map);
		if (null == user) {
			throw new ApiException(ErrorCode.USER_PASSWORD_ERROR);
		}
		map.put("newPwd", newPwd);
		map.put("userId", user.getUserId());
		// 修改密码
		userService.resetPassword(map);
		jsonView.setMessage("密码修改成功");
		jsonView.setResult(resultMap);
		return jsonView;
	}

	/**
	 * 修改手机号码1 验证旧手机号
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@Deprecated
	@ResponseBody
	@RequestMapping(value = "/phone/change", method = RequestMethod.POST)
	public JsonView appPhoneChange(@RequestBody UserSecurityForm form,
			HttpServletRequest request) throws ApiException {
		JsonView jsonView = new JsonView();
		String loginPhone = form.getOldPhone();
		String verifyCode = form.getOldVerifyCode();
		String verifyType = form.getOldVerifyType();
		if (!form.checkPhoneUnBind()) {
			throw new ApiException(ErrorCode.SYS_PARAM_NULL_ERROR);
		}
		if (!form.checkPhoneFormat(loginPhone)) {
			throw new ApiException(ErrorCode.USER_PHONE_FORMAT_ERROR);
		}
		// 数据库查询出验证码进行比对
		boolean isRight = validateVerifyCode(loginPhone, verifyCode, verifyType);
		// 验证码不一致
		if (!isRight) {
			throw new ApiException(ErrorCode.USER_VERIFY_ERROR);
		}
		jsonView.setMessage("验证通过");
		return jsonView;
	}

	/**
	 * 修改手机号码2下一步 验证新手机号
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 * @throws ApiException
	 */
	@Deprecated
	@ResponseBody
	@RequestMapping(value = "/phone/change/next", method = RequestMethod.POST)
	public JsonView appPhoneChangeNextStep(@RequestBody UserSecurityForm form,
			HttpServletRequest request) throws ApiException {
		Map<String, Object> map = new HashMap<String, Object>();
		JsonView jsonView = new JsonView();
		String token = request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		form.setUserId(userId);
		String loginPhone = form.getNewPhone();
		String verifyCode = form.getNewVerifyCode();
		String verifyType = form.getNewVerifyType();
		if (!form.checkPhoneBind()) {
			throw new ApiException(ErrorCode.SYS_PARAM_NULL_ERROR);
		}
		if (!form.checkPhoneFormat(loginPhone)) {
			throw new ApiException(ErrorCode.USER_PHONE_FORMAT_ERROR);
		}
		// 数据库查询出验证码进行比对
		boolean isRight = validateVerifyCode(loginPhone, verifyCode, verifyType);
		// 验证码不一致
		if (!isRight) {
			throw new ApiException(ErrorCode.USER_VERIFY_ERROR);
		}
		// 更新手机号
		map.put("userId", userId);
		map.put("loginPhone", loginPhone);
		userService.resetLoginPhone(map);
		jsonView.setMessage("手机号修改成功");
		return jsonView;
	}

	/**
	 * 验证码接口-->账号已注册返回验证码
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@ResponseBody
	@RequestMapping(value = "/open/verify/register", method = RequestMethod.POST)
	public JsonObjectView appHasRegisterVerify(@RequestBody UserInfoForm form,
			HttpServletRequest request) throws ApiException {
		Map<String, Object> map = new HashMap<String, Object>();
		JsonObjectView jsonView = new JsonObjectView();
		if (!form.checkPhone()) {
			throw new ApiException(ErrorCode.SYS_PARAM_NULL_ERROR);
		}
		if (!form.checkPhoneFormat()) {
			throw new ApiException(ErrorCode.USER_PHONE_FORMAT_ERROR);
		}
		String verifyType = form.getVerifyType();
		String phone = form.getLoginPhone();
		map.put("loginPhone", phone);
		int account = userService.getCountByAccount(map);
		if (!(account > 0)) {// 未注册过
			throw new ApiException(ErrorCode.USER_ACCOUNT_UNREGISTER);
		}
		// 查询字典验证码有效时间
		Dictionary dic = dictionaryService.selectByPrimaryKey("3100");
		// 查看此手机号是否存在未过期验的证码
		String dicExpire = dic.getDictionaryValue();
		Integer expire = 0;
		if (dicExpire != null || dicExpire != "") {
			expire = Integer.valueOf(dicExpire);
		}
		String template = "";
		if("0".equals(verifyType))//注册
			template=CarHomeApiProperties.MESSAGE_REGISTER_TEMPLATE;
		else if("1".equals(verifyType))//忘记密码
			template=CarHomeApiProperties.MESSAGE_PASSWORD_TEMPLATE;
//		map.put("verifyExpire", expire);
//		map.put("verifyType", verifyType);
//		VerifyCode code = verifyCodeService.getByCondition(map);
//		if (null != code) {
//			//发送短信
//			AliSMSUtil.sendMsg(phone,template, code.getVerifyCode());
//			jsonView.setMessage("验证码已发送到手机,请注意查收");
//			jsonView.setResult(code);
//			return jsonView;
//		}
		// 将验证码插入数据库
		String verifyCode = CommonUtil.getVerifyCode(4);
		VerifyCode verify = new VerifyCode();
		verify.setVerifyId(CommonUtil.getUID());
		verify.setVerifyCode(verifyCode);
		verify.setVerifyExpire(expire);
		verify.setPhone(phone);
		verify.setVerifyType(Integer.valueOf(verifyType));
		//发送短信
		AliSMSUtil.sendMsg(phone,template, verifyCode);
		verifyCodeService.insert(verify);
		jsonView.setMessage("验证码已发送到手机,请注意查收");
		jsonView.setResult(verify);
		return jsonView;
	}

	/**
	 * 验证码接口-->账号未注册返回验证码
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@ResponseBody
	@RequestMapping(value = "/open/verify/unregister", method = RequestMethod.POST)
	public JsonObjectView appUnRegisterVerify(@RequestBody UserInfoForm form,
			HttpServletRequest request) throws ApiException {
		Map<String, Object> map = new HashMap<String, Object>();
		JsonObjectView jsonView = new JsonObjectView();
		if (!form.checkPhone()) {
			throw new ApiException(ErrorCode.SYS_PARAM_NULL_ERROR);
		}
		if (!form.checkPhoneFormat()) {
			throw new ApiException(ErrorCode.USER_PHONE_FORMAT_ERROR);
		}
		String verifyType = form.getVerifyType();
		String phone = form.getLoginPhone();
		map.put("loginPhone", phone);
		int account = userService.getCountByAccount(map);
		if (account > 0) {// 已注册过
			throw new ApiException(ErrorCode.USER_ACCOUNT_REGISTER);
		}
		// 字典查询验证码有效时间
		Dictionary dic = dictionaryService.selectByPrimaryKey("3100");
		// 查看此手机号是否存在未过期的验证码
		String dicExpire = dic.getDictionaryValue();
		Integer expire = 0;
		if (dicExpire != null || dicExpire != "") {
			expire = Integer.valueOf(dicExpire);
		}
		
		String template = "";
		if("0".equals(verifyType))//注册
			template=CarHomeApiProperties.MESSAGE_REGISTER_TEMPLATE;
		else if("1".equals(verifyType))//忘记密码
			template=CarHomeApiProperties.MESSAGE_PASSWORD_TEMPLATE;
//		map.put("verifyExpire", expire);
//		map.put("verifyType", verifyType);
//		VerifyCode code = verifyCodeService.getByCondition(map);
//		if (null != code) {
//			//发送短信
//			AliSMSUtil.sendMsg(phone,template, code .getVerifyCode());
//			jsonView.setMessage("验证码已发送到手机,请注意查收");
//			jsonView.setResult(code);
//			return jsonView;
//		}
		// 将验证码插入数据库
		String verifyCode = CommonUtil.getVerifyCode(4);
		VerifyCode verify = new VerifyCode();
		verify.setVerifyId(CommonUtil.getUID());
		verify.setVerifyCode(verifyCode);
		verify.setVerifyExpire(expire);
		verify.setPhone(phone);
		verify.setVerifyType(Integer.valueOf(verifyType));
		//发送短信
		AliSMSUtil.sendMsg(phone,template, verifyCode);
		verifyCodeService.insert(verify);
		jsonView.setMessage("验证码已发送到手机,请注意查收");
		jsonView.setResult(verify);
		return jsonView;
	}

	/**
	 * 判断验证码是否一致
	 * 
	 * @param map
	 * @param verifyCode
	 * @return
	 */
	private boolean validateVerifyCode(String loginPhone, String verifyCode,
			String verifyType) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 查询字典验证码有效时间
		Dictionary dic = dictionaryService.selectByPrimaryKey("3100");
		map.put("loginPhone", loginPhone);
		map.put("verifyExpire", dic.getDictionaryValue());
		map.put("verifyType", verifyType);
		VerifyCode code = verifyCodeService.getByCondition(map);
		// 验证码不一致
		if (!(code != null && verifyCode.equals(code.getVerifyCode()))) {
			return false;
		}
		return true;
	}

}
