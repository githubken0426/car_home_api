package com.gtercn.carhome.web.api.service.experttype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.dao.ExpertTypeMapper;
import com.gtercn.carhome.web.api.entity.ExpertType;

@Service(value = "expertTypeService")
public class ExpertTypeServiceImpl implements ExpertTypeService {
	@Autowired
	private ExpertTypeMapper dao;


	@Override
	public ExpertType getDataById(String id) throws Exception {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateData(ExpertType o) throws Exception {
		return dao.updateByPrimaryKeySelective(o);
	}

	@Override
	public int addData(ExpertType o) throws Exception {
		return dao.insert(o);		
	}

	@Override
	public int deleteData(String id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public List<ExpertType> queryAllData() {
		return dao.queryAllData();
	}

}
