package com.gtercn.carhome.web.api.controller.server;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtercn.carhome.web.api.entity.Dictionary;
import com.gtercn.carhome.web.api.entity.Version;
import com.gtercn.carhome.web.api.service.dictionary.DictionaryService;
import com.gtercn.carhome.web.api.service.version.VersionService;
import com.gtercn.carhome.web.api.view.JsonView;

@Controller
@RequestMapping("/app/v1/open/server")
public class ServerControllerV1 {
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private VersionService versionService;

	@ResponseBody
	@RequestMapping(value = "/resources", method = RequestMethod.POST)
	public JsonView syncServerTime(HttpServletRequest request) {
		JsonView jv = new JsonView();
		// 查询验证码有效时间
		Dictionary dic = dictionaryService.selectByPrimaryKey("3100");
		jv.getResult().put("verify_expire", dic.getDictionaryValue());
		jv.getResult().put("server_time", System.currentTimeMillis() + "");
		// 返回版本信息
		String systemBj = request.getParameter("system_bj");
		Version version = versionService.getBySystemBj(systemBj);
		if (version != null) {
			jv.getResult().put("version_code", version.getVersionCode());
			jv.getResult().put("version_name", version.getVersionName());
			jv.getResult().put("content", version.getContent());
			jv.getResult().put("url", version.getUrl());
			jv.getResult().put("min_code", version.getMinCode());
			jv.getResult().put("min_version", version.getMinVersion());
			jv.getResult().put("min_content", version.getMinContent());
			if (systemBj.equals("0")) {
				jv.getResult().put("state", version.getState());
			}
		}
		return jv;
	}

}
