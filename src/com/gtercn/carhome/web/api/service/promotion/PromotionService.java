package com.gtercn.carhome.web.api.service.promotion;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.web.api.entity.Promotion;

public interface PromotionService {


	 /**
     * 详情
     * @param id
     * @return
     * 2017-3-14 上午09:55:24
     */
    Promotion selectPromotionDetail(Map<String,Object> map);

	/**
	 * 新增
	 * 
	 * @param o
	 * @return
	 */
	public int addData(Promotion o) throws Exception;

	/**
	 * 修改
	 * 
	 * @param o
	 * @return
	 */
	public int updateData(Promotion o) throws Exception;

	/**
	 * 删除
	 * 
	 * @param o
	 * @return
	 */
	public int deleteData(String id) throws Exception;
	
	/**
     * 查询所有促销信息
     * @return
     * 2016-12-23 下午03:13:01
     */
    public List<Promotion> selectAllData(Map<String,Object> map);
	
}
