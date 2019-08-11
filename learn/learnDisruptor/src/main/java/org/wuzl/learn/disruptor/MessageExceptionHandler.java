package org.wuzl.learn.disruptor;

import com.lmax.disruptor.ExceptionHandler;

/**
 * 异常处理类
 * 
 * @author ziliang.wu
 *
 */
public class MessageExceptionHandler implements ExceptionHandler<MessageEvent> {
	@Override
	public void handleEventException(Throwable ex, long sequence, MessageEvent event) {
		ex.printStackTrace();
	}

	@Override
	public void handleOnStartException(Throwable ex) {
		ex.printStackTrace();

	}

	@Override
	public void handleOnShutdownException(Throwable ex) {
		ex.printStackTrace();

	}
}
