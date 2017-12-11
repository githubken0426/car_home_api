package com.gtercn.carhome.web.api.service.experttype;

import java.util.List;

import com.gtercn.carhome.web.api.entity.ExpertType;

public interface ExpertTypeService {
	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public ExpertType getDataById(String id) throws Exception;

	/**
	 * 新增
	 * 
	 * @param o
	 * @return
	 */
	public int addData(ExpertType o) throws Exception;

	/**
	 * 修改
	 * 
	 * @param o
	 * @return
	 */
	public int updateData(ExpertType o) throws Exception;

	/**
	 * 删除
	 * 
	 * @param o
	 * @return
	 */
	public int deleteData(String id) throws Exception;
	
	/**
	 * 查询所有数据
	 * @return
	 * 2016-12-14 上午08:31:36
	 */
	public List<ExpertType> queryAllData();
	
}
