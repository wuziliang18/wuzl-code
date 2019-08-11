package org.family.learn.netty4.bean;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class Request implements Serializable {
	private static transient  AtomicLong idCreater=new AtomicLong(1l);
	public Request() {
		id=idCreater.getAndIncrement();
	}
	private Long id;
	private String from;
	private String message;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
