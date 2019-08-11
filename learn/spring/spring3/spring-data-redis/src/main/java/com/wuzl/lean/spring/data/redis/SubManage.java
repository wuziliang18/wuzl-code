package com.wuzl.lean.spring.data.redis;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;

import com.wuzl.lean.spring.data.redis.subscribe.listener.TopicMessageListener;

/**
 * 订阅管理
 * 
 * @author wuzl
 * 
 */
public class SubManage {
	private final ConcurrentHashMap<String, TopicMessageListener> SUB_MAP = new ConcurrentHashMap<String, TopicMessageListener>();
	private final ConcurrentHashMap<String, ChannelTopic> SUB_TOPIC_MAP = new ConcurrentHashMap<String, ChannelTopic>();
	private static final Log log = LogFactory.getLog(SubManage.class);
	@Autowired
	private RedisMessageListenerContainer listerContainer;

	/**
	 * 添加订阅
	 * 
	 * @param channelName
	 * @param listener
	 */
	public void addSub(String channelName, TopicMessageListener listener) {
		if (SUB_MAP.get(channelName) != null) {
			return;
		}
		synchronized (SUB_MAP) {
			if (SUB_MAP.get(channelName) != null) {
				return;
			}
			ChannelTopic topic = new ChannelTopic(channelName);
			listerContainer.addMessageListener(listener, topic);
			SUB_MAP.put(channelName, listener);
			SUB_TOPIC_MAP.put(channelName, topic);
			log.info("成功加入一个订阅，channel是" + channelName);
		}
	}

	/**
	 * 取消订阅
	 * 
	 * @param channelName
	 */
	public void removeSub(String channelName) {
		TopicMessageListener listener = SUB_MAP.remove(channelName);
		Topic topic = SUB_TOPIC_MAP.get(channelName);
		listerContainer.removeMessageListener(listener, topic);
		log.info("成功取消一个订阅，channel是" + channelName);
	}
}
