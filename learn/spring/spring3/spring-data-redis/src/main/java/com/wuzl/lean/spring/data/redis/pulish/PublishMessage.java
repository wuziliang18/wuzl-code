package com.wuzl.lean.spring.data.redis.pulish;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.wuzl.lean.spring.data.redis.pulish.interfaces.IPublishMessage;
import com.wuzl.lean.spring.data.redis.support.RedisSerializer;

public class PublishMessage implements IPublishMessage {
	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	@Override
	public void pulishMesage(String channel, Object msg) {
		if (msg == null) {
			return;
		}
		if (msg instanceof String) {
			this.pulishSimpleMesage(channel, (String)msg);
		}else{
			this.pulishSimpleMesage(channel, RedisSerializer.seriazileAsString(msg));
		}
	}

	@Override
	public void pulishSimpleMesage(String channel, String msg) {
		if (msg == null || "".equals(msg)) {
			return;
		}
		redisTemplate.convertAndSend(channel, msg);
	}

}
