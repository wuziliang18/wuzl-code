package com.wuzl.lean.spring.spring4.websocket.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 各种事件都在这里
 * 
 * @author wuzl
 * 
 */
public class ChatRoomWebSocket extends TextWebSocketHandler {
	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		TextMessage reply = new TextMessage(message.getPayload() + " 服务端回复");
		session.sendMessage(reply);
	}

	/**
	 * 握手成功 初始化操作在这里面进行
	 * 
	 * @return
	 */
	@Override
	public boolean supportsPartialMessages() {
		// 一旦HTTP认证成功 这个方法先被调用 如果返回true
		// 则进行上面那么N多方法的流程。如果返回的是false就直接拦截掉了。不会调用上面那些方法了！！
		// 就好像个构造器一样。这个是处理器 BootstrapHandler的构造器~
		return true;
	}
}
