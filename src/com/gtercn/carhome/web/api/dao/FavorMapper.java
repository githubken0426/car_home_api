package com.gtercn.carhome.web.api.dao;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.web.api.entity.Favor;

public interface FavorMapper {
	/**
	 * 插入
	 * @param record
	 * @return
	 * 2016-12-20 上午10:49:14
	 */
    int addFavor(Favor record);
    /**
     * 查询用户收藏
     * @return
     * 2016-12-20 上午10:49:52
     */
    public List<Favor> queryUserFavor(String userId);
    /**
     * 查询是否收藏过
     * 暂未启用
     * @param map
     * @return
     * 2016-12-22 上午09:12:35
     */
    public Favor queryIsUserFavored(Favor record);
    /**
     * 更改收藏状态
     * @param map
     * @return
     * 2017-1-22 下午02:10:04
     */
    int changeFavorById(Map<String,Object> map);
    /**
     * 取消收藏
     * @param id
     * @return
     * 2016-12-22 上午09:09:51
     */
    int cancelFavor(Map<String,Object> map);
}