package com.wuzl.lean.spring.data.redis.pulish.interfaces;

public interface IPublishMessage {
	/**
	 * 广播信息
	 * 
	 * @param channel
	 * @param msg
	 */
	public void pulishMesage(String channel, Object msg);

	/**
	 * 广播信息
	 * 
	 * @param channel
	 * @param msg
	 */
	public void pulishSimpleMesage(String channel, String msg);
}
