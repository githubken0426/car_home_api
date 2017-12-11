package com.gtercn.carhome.web.api.view;

import java.util.HashMap;
import java.util.Map;

public class JsonView {

	private String err_code = "0";
	private String err_message = "OK";
	private Map<String, Object> result;
	private String message;

	
	
	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_message() {
		return err_message;
	}

	public void setErr_message(String err_message) {
		this.err_message = err_message;
	}

	public Map<String, Object> getResult() {
		if (result == null)
			result = new HashMap<String, Object>();
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
