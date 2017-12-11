package com.gtercn.carhome.web.api.entity;

import com.gtercn.carhome.web.api.utils.FtpFilePath;


/**
 * 促销
 * 
 * @author ken
 * 2016-12-23 下午02:17:55
 */
public class Promotion {
    private String id;
    private String shopId;
    private String shopName;//店铺名称
    private String startDate;
    private String endDate;
    private String title;
    private String introduction;
    private String content;
    private String pictureList;
    private String backgroundImage;
    private String htmlUrl;
    private Integer deleteFlag;
    private String insertTime;
    private String updateTime;
    private String shopAddress;//公司地址
    private String shopPhone;//公司电话
    private String isFavored;
    private String cityCode;
    
    public String getIsFavored() {
		return isFavored;
	}

	public void setIsFavored(String isFavored) {
		this.isFavored = isFavored;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getPictureList() {
        return pictureList;
    }

    public void setPictureList(String pictureList) {
    	String url=(pictureList!=null && pictureList!="")?FtpFilePath.RESOURCES_IP+pictureList:pictureList;
    	this.pictureList = url;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopPhone() {
		return shopPhone;
	}

	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		String url=(htmlUrl!=null && htmlUrl!="")?FtpFilePath.RESOURCES_IP+htmlUrl:htmlUrl;
		this.htmlUrl = url;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(String backgroundImage) {
		String url=(backgroundImage!=null && backgroundImage!="")?FtpFilePath.RESOURCES_IP+backgroundImage:null;
		this.backgroundImage = url;
	}
	
}