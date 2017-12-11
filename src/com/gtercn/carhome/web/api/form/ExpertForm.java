package com.gtercn.carhome.web.api.form;

import com.gtercn.carhome.web.api.CarHomeApiProperties;

public class ExpertForm {
	private String expertType;
	private String cityCode;

	public String getExpertType() {
		return expertType;
	}

	public void setExpertType(String expertType) {
		this.expertType = expertType;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		String code = (cityCode == null || cityCode.equals("")) ? CarHomeApiProperties.DEFAULT_CITY_CODE
				: cityCode;
		this.cityCode = code;
	}

}
