package org.wuzl.learn.disruptor;

public class MessageEvent {
	/**
	 * 原始消息
	 */
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
