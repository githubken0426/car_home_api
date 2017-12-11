package com.gtercn.carhome.web.api.service.experttop;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.web.api.entity.ExpertTop;

public interface ExpertTopService {

	/**
	 * 查询
	 * 
	 * @return
	 */
	public List<ExpertTop> queryAllData(Map<String, Object> map);


	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public ExpertTop getDataById(String id) throws Exception;

	/**
	 * 新增
	 * 
	 * @param o
	 * @return
	 */
	public int addData(ExpertTop o) throws Exception;

	/**
	 * 修改
	 * 
	 * @param o
	 * @return
	 */
	public int updateData(ExpertTop o) throws Exception;

	/**
	 * 删除
	 * 
	 * @param o
	 * @return
	 */
	public int deleteData(String id) throws Exception;
	
}
