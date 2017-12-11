package com.gtercn.carhome.web.api.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gtercn.carhome.web.api.entity.Dictionary;

@Repository
public interface DictionaryMapper {
    int deleteByPrimaryKey(String dictionaryId);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);
    /**
     * 主键查询
     * @param dictionaryId
     * @return
     */
    Dictionary selectByPrimaryKey(String dictionaryId);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);
    
    /**
     * 数据类型区分查询
     * @param dictionaryTypeFlag
     * @return
     */
    List<Dictionary> selectByTypeFlag(String typeFlag);
    
    /**
     * 数据类型区分和值查询
     * @param dictionaryTypeFlag
     * @return
     */
    Dictionary selectByTypeFlagAndValue(Map<String, Object> map);
}