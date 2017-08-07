package com.mokazu.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SpringWebSocketHandler extends TextWebSocketHandler {
	private static final CopyOnWriteArraySet<WebSocketSession>	users;
	private static Logger										logger	= LogManager.getLogger(SpringWebSocketHandler.class);
	static {
		users = new CopyOnWriteArraySet<WebSocketSession>();
	}

	public SpringWebSocketHandler() {

	}

	/**
	 * 连接成功时候，会触发页面上onopen方法
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.add(session);
		logger.info("connect to the websocket success......total:{}", users.size());
		// 这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户
		// TextMessage returnMessage = new TextMessage("你将收到的离线");
		// session.sendMessage(returnMessage);
	}

	/**
	 * 关闭连接时触发
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		logger.debug("websocket connection closed......");
		String username = (String) session.getAttributes().get(SpringWebSocketConfig.WEBSOCKET_USERNAME);
		users.remove(session);
		logger.info("websocket user {} logout,left {}", username, users.size());
	}

	/**
	 * js调用websocket.send时候，会调用该方法
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		String username = (String) session.getAttributes().get(SpringWebSocketConfig.WEBSOCKET_USERNAME);
		logger.debug("receive text message: {}-{}", username, message.getPayload());
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		users.remove(session);
		if (session.isOpen()) {
			session.close();
		}
		logger.debug("websocket connection closed......");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给某个用户发送消息
	 *
	 * @param userName
	 * @param message
	 */
	public void sendMessageToUser(String userName, TextMessage message) {
		for (WebSocketSession user : users) {
			if (user.getAttributes().get(SpringWebSocketConfig.WEBSOCKET_USERNAME).equals(userName)) {
				try {
					if (user.isOpen()) {
						user.sendMessage(message);
					}
				} catch (IOException e) {
					logger.error(e);
				}
				break;
			}
		}
	}

	/**
	 * 给所有在线用户发送消息
	 *
	 * @param message
	 */
	public static void sendMessageToUsers(TextMessage message) {
		for (WebSocketSession user : users) {
			try {
				if (user.isOpen()) {
					user.sendMessage(message);
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}

	public static CopyOnWriteArraySet<WebSocketSession> getUsers() {
		return users;
	}

}