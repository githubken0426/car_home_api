package com.gtercn.carhome.web.api.service.promotion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.dao.PromotionMapper;
import com.gtercn.carhome.web.api.entity.Promotion;

@Service(value = "promotionService")
public class PromotionServiceImpl implements PromotionService {
	@Autowired
	private PromotionMapper dao;

	@Override
	public int updateData(Promotion o) throws Exception {
		return dao.updateByPrimaryKeySelective(o);
	}

	@Override
	public int addData(Promotion o) throws Exception {
		return dao.insert(o);		
	}

	@Override
	public int deleteData(String id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Promotion> selectAllData(Map<String,Object> map) {
		return dao.selectAllData(map);
	}

	@Override
	public Promotion selectPromotionDetail(Map<String, Object> map) {
		return dao.selectPromotionDetail(map);
	}

}
