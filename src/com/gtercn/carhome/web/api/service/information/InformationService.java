package com.gtercn.carhome.web.api.service.information;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.web.api.entity.Information;

public interface InformationService {

	/**
	 * 查询
	 * 
	 * @return
	 */
	public List<Information> selectAllData(Map<String,Object> map);


	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public Information selectInformationDetail(Map<String,Object> map);

	/**
	 * 新增
	 * 
	 * @param o
	 * @return
	 */
	public int addData(Information o) throws Exception;

	/**
	 * 修改
	 * 
	 * @param o
	 * @return
	 */
	public int updateData(Information o) throws Exception;

	/**
	 * 删除
	 * 
	 * @param o
	 * @return
	 */
	public int deleteData(String id) throws Exception;
	
}
