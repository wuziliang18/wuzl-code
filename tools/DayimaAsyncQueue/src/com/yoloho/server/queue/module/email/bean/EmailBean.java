package com.yoloho.server.queue.module.email.bean;

import com.alibaba.fastjson.JSON;

public class EmailBean {
	private String to;
	private String sub;
	private String text;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
