package com.gtercn.carhome.web.api.service.experttop;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.dao.ExpertTopMapper;
import com.gtercn.carhome.web.api.entity.ExpertTop;

@Service(value = "expertTopService")
public class ExpertTopServiceImpl implements ExpertTopService {
	@Autowired
	private ExpertTopMapper dao;

	@Override
	public List<ExpertTop> queryAllData(Map<String, Object> map) {
		return dao.queryAllData(map);
	}

	@Override
	public ExpertTop getDataById(String id) throws Exception {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateData(ExpertTop o) throws Exception {
		return dao.updateByPrimaryKeySelective(o);
	}

	@Override
	public int addData(ExpertTop o) throws Exception {
		return dao.insert(o);		
	}

	@Override
	public int deleteData(String id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

}
