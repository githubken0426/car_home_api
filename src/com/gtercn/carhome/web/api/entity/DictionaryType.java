package com.gtercn.carhome.web.api.entity;

import java.util.Date;

public class DictionaryType {
    private String dictionaryTypeId;
    private String dictionaryType;
    private String dictionaryName;
    private Integer deleteFlag;
    private Date updateTime;
    private Date insertTime;

    public String getDictionaryTypeId() {
        return dictionaryTypeId;
    }

    public void setDictionaryTypeId(String dictionaryTypeId) {
        this.dictionaryTypeId = dictionaryTypeId == null ? null : dictionaryTypeId.trim();
    }

    public String getDictionaryType() {
        return dictionaryType;
    }

    public void setDictionaryType(String dictionaryType) {
        this.dictionaryType = dictionaryType == null ? null : dictionaryType.trim();
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName == null ? null : dictionaryName.trim();
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}