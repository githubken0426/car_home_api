package com.gtercn.carhome.web.api.controller.user;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gtercn.carhome.web.api.entity.Favor;
import com.gtercn.carhome.web.api.entity.QuestionArticle;
import com.gtercn.carhome.web.api.entity.User;
import com.gtercn.carhome.web.api.enums.ErrorCode;
import com.gtercn.carhome.web.api.exception.ApiException;
import com.gtercn.carhome.web.api.service.favor.FavorService;
import com.gtercn.carhome.web.api.service.querstionarticle.QuestionArticleService;
import com.gtercn.carhome.web.api.service.user.IUserService;
import com.gtercn.carhome.web.api.utils.CommonRegex;
import com.gtercn.carhome.web.api.utils.CommonUtil;
import com.gtercn.carhome.web.api.utils.Encrypt;
import com.gtercn.carhome.web.api.utils.FtpFilePath;
import com.gtercn.carhome.web.api.utils.FtpFileTools;
import com.gtercn.carhome.web.api.view.JsonList;
import com.gtercn.carhome.web.api.view.JsonObjectView;
import com.gtercn.carhome.web.api.view.JsonView;

/**
 * 用户信息相关
 * 
 * @author Administrator
 * 
 *         2016-8-4 上午10:10:26
 */
@Controller
@RequestMapping(value = "/app/v1/user")
public class UserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private QuestionArticleService questionArticleService;
	@Autowired
	private FavorService favorService;

	/**
	 * 用户信息更改 头像，昵称，真实姓名，性别
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws ApiException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/userinfo/change", method = RequestMethod.POST)
	public JsonView appAvatarChange(
			@RequestParam(value = "avatar", required = false) MultipartFile avatar,
			HttpServletRequest request) throws ApiException, IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		JsonView jsonView = new JsonView();
		String token = request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);

		String nickname = request.getParameter("nickname");
		if (StringUtils.isNotBlank(nickname) && !Pattern.matches(CommonRegex.NICKNAME, nickname)) {
			throw new ApiException(ErrorCode.USER_NICKNAME_FORMAT_ERROR);
		}
		String realName = request.getParameter("real_name");
		String sex = request.getParameter("sex");
		map.put("userId", userId);
		map.put("nickname", nickname);
		map.put("realName", realName);
		map.put("sex", sex);
		if (avatar!=null) {
			String[] savePath = { FtpFilePath.ROOT_PATH,
					FtpFilePath.USERS_PATH, FtpFilePath.AVATAR_PATH, userId };
			String fileName = CommonUtil.getUID() + ".jpg";
			boolean bool = FtpFileTools.uploadFile(savePath, fileName, avatar
					.getInputStream());
			if (!bool) {
				resultMap.put("result", "头像上传失败");
				jsonView.setResult(resultMap);
				// throw new ApiException(ErrorCode.USER_AVATAR_UPLOAD_ERROR);
			}
			String path = File.separator + FtpFilePath.ROOT_PATH
					+ File.separator + FtpFilePath.USERS_PATH + File.separator
					+ FtpFilePath.AVATAR_PATH + File.separator + userId
					+ File.separator + fileName;
			resultMap.put("avatar_url", FtpFilePath.RESOURCES_IP+ path);
			map.put("avatarUrl", path);
		}
		userService.updateUserPersonlInfo(map);
		jsonView.setResult(resultMap);
		jsonView.setMessage("修改成功");
		return jsonView;
	}

	/**
	 * 个人中心--》个人信息
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws ApiException
	 *             2016-12-19 上午09:23:39
	 */
	@ResponseBody
	@RequestMapping(value = "/personal/info", method = RequestMethod.POST)
	public JsonObjectView appPersonlInfo(HttpServletRequest request)
			throws ApiException {
		JsonObjectView jsonView = new JsonObjectView();
		String token = request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		User user = userService.getPersonalInfo(userId);
		jsonView.setResult(user);
		return jsonView;
	}

	/**
	 * 个人中心--》我的收藏
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws ApiException
	 *             2016-12-19 上午09:23:39
	 */
	@ResponseBody
	@RequestMapping(value = "/personal/favor", method = RequestMethod.POST)
	public JsonList<Favor> appPersonlFavor(HttpServletRequest request)
			throws ApiException {
		JsonList<Favor> json = new JsonList<Favor>();
		String token = request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		List<Favor> list = favorService.queryUserFavor(userId);
		json.setResult(list);
		if (list.size() == 0) {
			json.setMessage("你还没有收藏信息");
		}
		return json;
	}

	/**
	 * 个人中心--》我的问题
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws ApiException
	 *             2016-12-19 上午09:23:39
	 */
	@ResponseBody
	@RequestMapping(value = "/personal/question", method = RequestMethod.POST)
	public JsonList<QuestionArticle> appPersonlQuestion(
			HttpServletRequest request) throws ApiException {
		JsonList<QuestionArticle> jsonView = new JsonList<QuestionArticle>();
		Map<String, Object> map = new HashMap<String, Object>();
		String token = request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		map.put("userId", userId);
		map.put("type", 1);// 类型 1:问题墙 2:车友会 3:文章
		List<QuestionArticle> result = questionArticleService
				.queryMyArticleQuestion(map);
		jsonView.setResult(result);
		if (result.size() == 0) {
			jsonView.setMessage("你还没有提出问题");
		}
		return jsonView;
	}

	/**
	 * 个人中心--》我的文章
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws ApiException
	 *             2016-12-19 上午09:23:39
	 */
	@ResponseBody
	@RequestMapping(value = "/personal/article", method = RequestMethod.POST)
	public JsonList<QuestionArticle> appPersonlArticle(
			HttpServletRequest request) throws ApiException {
		JsonList<QuestionArticle> jsonView = new JsonList<QuestionArticle>();
		Map<String, Object> map = new HashMap<String, Object>();
		String token = request.getParameter("token");
		String userId = Encrypt.getEncryptUserId(token);
		map.put("userId", userId);
		map.put("type", 3);// 类型 1:问题墙 2:车友会 3:文章
		List<QuestionArticle> result = questionArticleService
				.queryMyArticleQuestion(map);
		jsonView.setResult(result);
		if (result.size() == 0) {
			jsonView.setMessage("你还没有发表文章");
		}
		return jsonView;
	}
}
