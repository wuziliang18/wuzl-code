package com.wuzl.lean.spring.data.redis.bean;

/**
 * 聊天记录
 * 
 * @author wuzl
 * 
 */
public class ChatRecordBean {
	private long id;
	private long uid;
	private String message;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
