package com.mokazu.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class SpringWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	public static String	WEBSOCKET_USERNAME	= "WEBSOCKET_USERNAME";

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler(), "/websocket/server").addInterceptors(new SpringWebSocketHandlerInterceptor());
		registry.addHandler(webSocketHandler(), "/sockjs/server").addInterceptors(new SpringWebSocketHandlerInterceptor()).withSockJS();
	}

	@Bean
	public TextWebSocketHandler webSocketHandler() {
		return new SpringWebSocketHandler();
	}

}