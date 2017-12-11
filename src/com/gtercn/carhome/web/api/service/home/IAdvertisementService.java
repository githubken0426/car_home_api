package com.gtercn.carhome.web.api.service.home;

import java.util.List;

import com.gtercn.carhome.web.api.entity.Advertisement;

public interface IAdvertisementService {
	//查询活动分类模块
	public List<Advertisement> getActivitySort();
	//查询顶部广告模块
	public List<Advertisement> getTopAdvertisement();
	//查询底部优惠推荐广告部分
	public List<Advertisement> getBottomAdvertisement();
	

}
