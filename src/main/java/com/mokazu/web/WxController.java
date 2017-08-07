package com.mokazu.web;

import java.util.Calendar;

import me.chanjar.weixin.common.exception.WxErrorException;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mokazu.config.WxApi;
import com.mokazu.dto.ValueColor;
import com.mokazu.entity.Order;
import com.mokazu.entity.Product;
import com.mokazu.service.IOrderService;
import com.mokazu.service.IProductService;
import com.mokazu.service.impl.WeixinService;

@RestController
@RequestMapping("/wx")
public class WxController {
	@Autowired
	private WeixinService	wxService;
	@Autowired
	private IProductService	productService;
	@Autowired
	private IOrderService	orderService;

	@RequestMapping("/token")
	public String token() throws WxErrorException {
		return wxService.getAccessToken();
	}

	@RequestMapping("/consume")
	public boolean consume(@RequestParam("code") String code, @RequestParam("openid") String openid, @RequestParam("username") String username, @RequestParam("address") String address,
			@RequestParam("mobile") String mobile, @RequestParam("cardid") String cardid) {
		JSONObject jo = new JSONObject();
		jo.put("encrypt_code", code);
		try {
			String response = wxService.post(WxApi.decrypt, jo.toJSONString());
			JSONObject ro = JSONObject.parseObject(response);
			if (ro.getInteger("errcode") == 0) {
				String recode = ro.getString("code");
				jo = new JSONObject();
				jo.put("code", recode);
				ro = JSONObject.parseObject(wxService.post(WxApi.consume, jo.toJSONString()));
				boolean re = ro.getInteger("errcode") == 0;
				if (re) {
					Product product = productService.selectOne(new EntityWrapper<Product>().where("cardId={0}", cardid));
					jo = new JSONObject();
					jo.put("touser", openid);
					jo.put("template_id", WxApi.template);
					jo.put("url", "");
					jo.put("topcolor", "#FF0000");
					JSONObject data = new JSONObject();
					if (product == null) {
						product = new Product();
					}
					data.put("first", new ValueColor(product.getFirst(), "#173177"));
					data.put("product", new ValueColor(product.getName(), "#173177"));
					data.put("price", new ValueColor(product.getPrice(), "#173177"));
					data.put("time", new ValueColor(DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd hh:mm:ss"), "#173177"));
					data.put("remark", new ValueColor(product.getRemark(), "#173177"));
					jo.put("data", data);
					wxService.post(WxApi.send, jo.toJSONString());
					data.put("first", new ValueColor("有客户消费礼品卡了，赶紧去发货吧！", "#173177"));
					data.put("remark", new ValueColor("发货地址：" + address + " " + username + " " + mobile, "#173177"));
					jo.put("touser", "or6LL1MvF7-ZTUPjlKwz_Juwomog");
					wxService.post(WxApi.send, jo.toJSONString());
					jo.put("touser", "or6LL1OlDjIyLcQzImta2kX9xu_A");
					wxService.post(WxApi.send, jo.toJSONString());
					Order order = new Order();
					order.setCode(recode);
					order.setCardId(cardid);
					order.setCardName(product.getName());
					order.setUsername(username);
					order.setAddress(address);
					order.setMobile(mobile);
					order.setStatus("已下单");
					orderService.insert(order);
				}
				return re;
			} else {
				return false;
			}
		} catch (WxErrorException e) {
			e.printStackTrace();
			return false;
		}
	}
}
