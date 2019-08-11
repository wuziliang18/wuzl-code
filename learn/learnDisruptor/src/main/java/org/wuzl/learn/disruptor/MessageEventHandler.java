package org.wuzl.learn.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * 消息事件处理类，这里只打印消息
 * 
 * @author ziliang.wu
 *
 */
public class MessageEventHandler implements EventHandler<MessageEvent>, WorkHandler<MessageEvent> {
	@Override
	public void onEvent(MessageEvent messageEvent, long l, boolean b) throws Exception {
		Thread thread = Thread.currentThread();
		Thread.sleep(100l);
		System.out.println(thread.getName() + ":" + thread.getId() + ":" + messageEvent.getMessage());
	}

	@Override
	public void onEvent(MessageEvent event) throws Exception {
		this.onEvent(event, 0, true);

	}
	 public static void main(String[] args) {
			System.out.println(4<<3);
		}
}
