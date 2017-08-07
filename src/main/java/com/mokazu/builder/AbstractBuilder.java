package com.mokazu.builder;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mokazu.service.impl.WeixinService;

/**
 * 
 * @author Binary Wang
 *
 */
public abstract class AbstractBuilder {
	protected final Logger	logger	= LoggerFactory.getLogger(getClass());

	public abstract WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WeixinService service);
}
