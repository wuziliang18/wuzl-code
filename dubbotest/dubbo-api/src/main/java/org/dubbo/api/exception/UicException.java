package org.dubbo.api.exception;

public class UicException extends RuntimeException {
	private String errorMsg;
	private int code;
	public UicException(String msg,int code) {
		super(msg);
		this.errorMsg=msg;
		this.code=code;
	}
	public String getMsg(){
		return errorMsg;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
}
