package com.gtercn.carhome.web.api.dao;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.web.api.entity.ExpertTop;

public interface ExpertTopMapper {
	/**
	 * 查询所有数据
	 * @return
	 * 2016-12-14 上午09:32:41
	 */
	public List<ExpertTop> queryAllData(Map<String,Object> map);
	
    int deleteByPrimaryKey(String id);

    int insert(ExpertTop record);

    int insertSelective(ExpertTop record);

    ExpertTop selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExpertTop record);

    int updateByPrimaryKey(ExpertTop record);
}