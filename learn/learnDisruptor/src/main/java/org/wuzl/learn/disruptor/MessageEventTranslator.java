package org.wuzl.learn.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * 消息转换类，负责将消息转换为事件
 * 
 * @author ziliang.wu
 *
 */
public class MessageEventTranslator implements EventTranslatorOneArg<MessageEvent, String> {
	@Override
	public void translateTo(MessageEvent messageEvent, long l, String s) {
		messageEvent.setMessage(s);
	}
}
