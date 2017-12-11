package com.gtercn.carhome.web.api.service.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.dao.AdvertisementMapper;
import com.gtercn.carhome.web.api.entity.Advertisement;
@Service
public class AdvertisementService implements IAdvertisementService {
    @Autowired
    private AdvertisementMapper entityDao;

	@Override
	public List<Advertisement> getActivitySort() {
		// 查询活动分类模块
		return entityDao.getActivitySort();
	}
	@Override
	public List<Advertisement> getTopAdvertisement() {
		// 查询顶部广告模块
		return entityDao.getTopAdvertisement();
	}
	@Override
	public List<Advertisement> getBottomAdvertisement() {
		// 查询底部优惠推荐广告部分
		return entityDao.getBottomAdvertisement();
	}

}
