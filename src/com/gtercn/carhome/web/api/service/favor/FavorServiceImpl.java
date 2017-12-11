package com.gtercn.carhome.web.api.service.favor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.dao.FavorMapper;
import com.gtercn.carhome.web.api.entity.Favor;

@Service(value = "favorService")
public class FavorServiceImpl implements FavorService {
	@Autowired
	private FavorMapper dao;
	
	@Override
	public int saveOrUpdate(Favor record) {
		Favor favored=dao.queryIsUserFavored(record);
		if(favored!=null){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id",favored.getId());
			map.put("deleteFlag",0);
			return dao.changeFavorById(map);
		}
		return dao.addFavor(record);
	}

	@Override
	public List<Favor> queryUserFavor(String userId) {
		return dao.queryUserFavor(userId);
	}

	@Override
	public int cancelFavor(Map<String, Object> map) {
		return dao.cancelFavor(map);
	}
}
