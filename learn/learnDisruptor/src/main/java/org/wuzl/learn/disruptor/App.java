package org.wuzl.learn.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class App {
	public static void main(String[] args) {
		String message = "Hello Disruptor!";
		int ringBufferSize = 1024;// 必须是2的N次方
		Disruptor<MessageEvent> disruptor = new Disruptor<MessageEvent>(new MessageEventFactory(), ringBufferSize,
				new MessageThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());
		MessageEventHandler handle = new MessageEventHandler();
		// disruptor.handleEventsWith(handle);
		// disruptor.handleEventsWith(handle, handle, handle);//
		// 每个handle互不干扰都要执行
		disruptor.handleEventsWithWorkerPool(handle, handle, handle, handle);// 只有一个执行就可以
		// disruptor.handleEventsWithWorkerPool(new MessageEventHandler(), new
		// MessageEventHandler(),
		// new MessageEventHandler());// 只有一个执行就可以
		disruptor.setDefaultExceptionHandler(new MessageExceptionHandler());
		RingBuffer<MessageEvent> ringBuffer = disruptor.start();
		MessageEventProducer producer = new MessageEventProducer(ringBuffer);
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			producer.onData(message + i);
		}
		System.out.println("花费时间" + (System.currentTimeMillis() - start) / 1000);
		disruptor.shutdown();
	}
}
