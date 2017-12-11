package com.gtercn.carhome.web.api.service.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.dao.DictionaryMapper;
import com.gtercn.carhome.web.api.entity.Dictionary;

@Service(value="dictionaryService")
public class DictionaryService implements IDictionaryService {
	@Autowired
	private DictionaryMapper dictionaryDao;
	
	@Override
	public Dictionary selectByPrimaryKey(String dictionaryId) {
		return dictionaryDao.selectByPrimaryKey(dictionaryId);
	}

}
