package com.gtercn.carhome.web.api.service.favor;

import java.util.List;
import java.util.Map;

import com.gtercn.carhome.web.api.entity.Favor;

public interface FavorService {
	/**
     * 查询用户收藏
     * @return
     * 2016-12-20 上午10:49:52
     */
    public List<Favor> queryUserFavor(String userId);
    /**
	 * 插入
	 * @param record
	 * @return
	 * 2016-12-20 上午10:49:14
	 */
    int saveOrUpdate(Favor record);
    /**
     * 取消收藏
     * @param id
     * @return
     * 2016-12-22 上午09:09:51
     */
    int cancelFavor(Map<String,Object> map);
}
