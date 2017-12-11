package com.gtercn.carhome.web.api.dao;

import com.gtercn.carhome.web.api.entity.DictionaryType;

public interface DictionaryTypeMapper {
	
    int deleteByPrimaryKey(String dictionaryTypeId);

    int insert(DictionaryType record);

    int insertSelective(DictionaryType record);

    DictionaryType selectByPrimaryKey(String dictionaryTypeId);

    int updateByPrimaryKeySelective(DictionaryType record);

    int updateByPrimaryKey(DictionaryType record);
}