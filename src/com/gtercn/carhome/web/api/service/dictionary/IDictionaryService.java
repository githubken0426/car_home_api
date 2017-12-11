package com.gtercn.carhome.web.api.service.dictionary;

import com.gtercn.carhome.web.api.entity.Dictionary;

public interface IDictionaryService {
	/**
     * 主键查询
     * @param dictionaryId
     * @return
     */
	public Dictionary selectByPrimaryKey(String dictionaryId);
    
}
