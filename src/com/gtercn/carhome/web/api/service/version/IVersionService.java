package com.gtercn.carhome.web.api.service.version;


import com.gtercn.carhome.web.api.entity.Version;

public abstract interface IVersionService {
	Version selectByPrimaryKey(String id);
	//通过系统标识获取版本信息
	public Version getBySystemBj(String systemBj);
}