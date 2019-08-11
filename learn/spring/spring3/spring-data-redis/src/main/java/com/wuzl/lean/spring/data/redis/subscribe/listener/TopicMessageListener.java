package com.wuzl.lean.spring.data.redis.subscribe.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import com.wuzl.lean.spring.data.redis.support.RedisSerializer;

public abstract class TopicMessageListener implements MessageListener {
	private Class<?> clazz;

	public TopicMessageListener(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void onMessage(Message message, byte[] pattern) {
		byte[] body = message.getBody();
		// 注意如果多个渠道 方法内多线程
		if (clazz.equals(String.class)) {
			operatorMessage(new String(message.getChannel()), new String(body));
		} else {
			operatorMessage(new String(message.getChannel()),
					RedisSerializer
							.deserializeAsObject(new String(body), clazz));
		}

	}

	protected abstract void operatorMessage(String channelName, Object object);
}
