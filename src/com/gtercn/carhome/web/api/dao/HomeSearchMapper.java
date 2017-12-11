package com.gtercn.carhome.web.api.dao;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.web.api.entity.Rescue;

public interface HomeSearchMapper {
	/**
	 * 获取搜索结果
	 * @param map
	 * @return
	 * 2017-1-10 下午01:58:18
	 */
	public List<Rescue> getSearchData(Map<String,Object> map);
}
