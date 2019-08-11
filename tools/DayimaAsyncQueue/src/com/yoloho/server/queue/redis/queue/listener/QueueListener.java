package com.yoloho.server.queue.redis.queue.listener;
/**
 * 队列的监听接口 
 * @author wuzl
 *
 * @param <T>
 */
public interface QueueListener<T>{
	public void onMessage(T message, String queueName) ; 
}
