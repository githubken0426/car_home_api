package com.gtercn.carhome.web.api.service.information;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.dao.InformationMapper;
import com.gtercn.carhome.web.api.entity.Information;

@Service(value = "informationService")
public class InformationServiceImpl implements InformationService {
	@Autowired
	private InformationMapper dao;

	@Override
	public List<Information> selectAllData(Map<String,Object> map) {
		return dao.selectAllData(map);
	}

	@Override
	public Information selectInformationDetail(Map<String,Object> map){
		return dao.selectInformationDetail(map);
	}

	@Override
	public int updateData(Information o) throws Exception {
		return dao.updateByPrimaryKeySelective(o);
	}


	@Override
	public int addData(Information o) throws Exception {
		return dao.insert(o);		
	}

	@Override
	public int deleteData(String id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

}
