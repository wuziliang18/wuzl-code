package org.wuzl.learn.disruptor;

import com.lmax.disruptor.EventFactory;

public class MessageEventFactory implements EventFactory<MessageEvent> {
	@Override
	public MessageEvent newInstance() {
		return new MessageEvent();
	}
}
