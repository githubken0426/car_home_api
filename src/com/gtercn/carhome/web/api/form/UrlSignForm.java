package com.gtercn.carhome.web.api.form;

public class UrlSignForm {

	private String url;
	private String userid;
	private String timestamp;
	private String sign;

	public UrlSignForm(String _url, String _userid, String _timestamp,
			String _sign) {
		this.url = _url;
		this.userid = _userid;
		this.timestamp = _timestamp;
		this.sign = _sign;

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "UrlSignForm [sign=" + sign + ", timestamp=" + timestamp
				+ ", url=" + url + ", userid=" + userid + "]";
	}

}