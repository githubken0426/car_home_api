package com.gtercn.carhome.web.api.exception;

import com.gtercn.carhome.web.api.enums.ErrorCode;

public class ApiException extends Exception {

	private static final long serialVersionUID = -1611719326185520599L;
	private ErrorCode errcode;

	public ApiException(String errmsg) {
		super(errmsg);
	}

	public ApiException(ErrorCode errcode) {
		super(errcode.getCodeMessage() + ",errcode " + errcode.getCode());
		this.errcode = errcode;
	}

	public static void main(String[] args) throws ApiException {
		throw new ApiException(ErrorCode.API_EXPIRED_CODE);
	}

	public ErrorCode getErrcode() {
		return errcode;
	}

	public void setErrcode(ErrorCode errcode) {
		this.errcode = errcode;
	}

}