package com.wuzl.lean.spring.data.redis.chat;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.wuzl.lean.spring.data.redis.bean.ChatRecordBean;
import com.wuzl.lean.spring.data.redis.subscribe.listener.TopicMessageListener;

public class ChatRecordMessageOperator extends TopicMessageListener {
	private final List<ChatRecordBean> rows = new CopyOnWriteArrayList<ChatRecordBean>();
	private final int MAX_RECORD = 3000;

	public ChatRecordMessageOperator() {
		super(ChatRecordBean.class);
	}

	@Override
	protected void operatorMessage(String channelName, Object object) {
		rows.add((ChatRecordBean) object);
		int size = rows.size();
		if (size > MAX_RECORD) {
			int removeSize = size - MAX_RECORD;
			for (int i = removeSize; i >= 0; i--) {
				rows.remove(i);
			}
		}
	}

}
