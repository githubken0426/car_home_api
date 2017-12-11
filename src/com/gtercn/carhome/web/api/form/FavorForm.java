package com.gtercn.carhome.web.api.form;

import org.apache.commons.lang.StringUtils;

public class FavorForm {
	private String favorId;
	private String favorType;
	
	public boolean checkFavor(){
		return StringUtils.isNotBlank(this.favorId) &&  StringUtils.isNotBlank(this.favorType);
	}

	public String getFavorId() {
		return favorId;
	}
	public void setFavorId(String favorId) {
		this.favorId = favorId;
	}
	public String getFavorType() {
		return favorType;
	}
	public void setFavorType(String favorType) {
		this.favorType = favorType;
	}
}
