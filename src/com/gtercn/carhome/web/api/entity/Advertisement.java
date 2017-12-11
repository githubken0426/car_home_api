package com.gtercn.carhome.web.api.entity;

import java.util.Date;

public class Advertisement {
    private String advertisementId;

    private String pageUrl;

    private String pictureId;

    private Integer advertisementType;

    private Integer sortNo;

    private Integer flag;

    private Integer deleteFlag;

    private Date updateTime;

    private Date insertTime;

    public String getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(String advertisementId) {
        this.advertisementId = advertisementId == null ? null : advertisementId.trim();
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl == null ? null : pageUrl.trim();
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId == null ? null : pictureId.trim();
    }

    public Integer getAdvertisementType() {
        return advertisementType;
    }

    public void setAdvertisementType(Integer advertisementType) {
        this.advertisementType = advertisementType;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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