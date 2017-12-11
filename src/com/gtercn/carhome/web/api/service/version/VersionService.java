package com.gtercn.carhome.web.api.service.version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.dao.VersionMapper;
import com.gtercn.carhome.web.api.entity.Version;

@Service
public class VersionService implements IVersionService{
	@Autowired
	private VersionMapper versionDao;
	
	@Override
	public Version selectByPrimaryKey(String id) {
		return versionDao.selectByPrimaryKey(id);
	}

	@Override
	public Version getBySystemBj(String systemBj) {
		return versionDao.getBySystemBj(systemBj);
	}
}
