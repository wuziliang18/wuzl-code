package org.family.learn.netty4.bean;

import java.io.Serializable;

public class Response implements Serializable {
	private Long id;
	private String returnMsg;

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
