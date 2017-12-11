package com.gtercn.carhome.web.api.entity;

import com.gtercn.carhome.web.api.utils.AppendServerURL;
import com.gtercn.carhome.web.api.utils.FtpFilePath;

/**
 * 资讯
 * 
 * @author ken
 * 2016-12-23 下午02:18:04
 */
public class Information {
    private String id;
    private String title;
    private String content;
    private String resource;
    private String pictureList;
    private Integer deleteFlag;
    private String insertTime;
    private String updateTime;
    private String htmlUrl;
    private String isFavored;
    private String introduction;

	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource == null ? null : resource.trim();
    }

    public String getPictureList() {
        return pictureList;
    }

    public void setPictureList(String pictureList) {
    	this.pictureList = AppendServerURL.appendFtpConfigPath(pictureList);
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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

	public String getIsFavored() {
		return isFavored;
	}

	public void setIsFavored(String isFavored) {
		this.isFavored = isFavored;
	}
}