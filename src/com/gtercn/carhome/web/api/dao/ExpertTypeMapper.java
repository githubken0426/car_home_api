package com.gtercn.carhome.web.api.dao;

import java.util.List;

import com.gtercn.carhome.web.api.entity.ExpertType;

public interface ExpertTypeMapper {
	/**
	 * 查询所有数据
	 * @return
	 * 2016-12-14 上午08:31:36
	 */
	public List<ExpertType> queryAllData();
	
    int deleteByPrimaryKey(String id);

    int insert(ExpertType record);

    int insertSelective(ExpertType record);

    ExpertType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExpertType record);

    int updateByPrimaryKey(ExpertType record);
}