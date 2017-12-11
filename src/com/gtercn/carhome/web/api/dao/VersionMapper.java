package com.gtercn.carhome.web.api.dao;

import com.gtercn.carhome.web.api.entity.Version;


public interface VersionMapper {
    Version selectByPrimaryKey(String id);
    //通过系统标识获取版本信息
  	public Version getBySystemBj(String systemBj);
}