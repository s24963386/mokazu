package com.mokazu.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.common.exception.WxErrorException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mokazu.common.JsonResult;
import com.mokazu.config.WxApi;
import com.mokazu.entity.Product;
import com.mokazu.service.IProductService;
import com.mokazu.service.impl.WeixinService;
import com.mokazu.utils.Utils;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jesen.shen
 * @since 2017-07-27
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

	@Autowired
	private WeixinService	wxService;

	@Autowired
	private IProductService	productService;

	@ResponseBody
	@RequestMapping("/list")
	public JsonResult list() {
		return renderSuccess(productService.selectList(Condition.Empty()));
	}

	@ResponseBody
	@RequestMapping("/add")
	public JsonResult add(Product product, HttpServletRequest request) {
		try {
			String filePath = ProductController.class.getResource("/coupon.json").getPath();
			String json = FileUtils.readFileToString(new File(filePath));
			Map<String, String> args = new HashMap<String, String>();
			args.put("coupon_title", product.getName());
			if (product.getName().indexOf("旗舰") >= 0) {
				args.put("coupon_icon", "http://mmbiz.qpic.cn/mmbiz_jpg/BtLWM4ibu8Z4NcjH4UnkavHyKhA8ibGriapl2qYQQWEK2P34qbXeLiavibFNBr7bEtFES2TKia5k4zhib0I3Hp3dtQg7A/0?wx_fmt=jpeg");
			} else {
				args.put("coupon_icon", "http://mmbiz.qpic.cn/mmbiz_jpg/BtLWM4ibu8Z4NcjH4UnkavHyKhA8ibGriapfichOcyGGsWntZorCmDt4ubujKk2KV2DHLxib92rfAEF2NPdwympULWw/0?wx_fmt=jpeg");
			}
			json = Utils.replace(json, args);
			JSONObject jo = JSONObject.parseObject(json);
			jo = JSONObject.parseObject(wxService.post(WxApi.card_create, jo.toJSONString()));
			if (jo.getInteger("errcode") == 0) {
				product.setCreateTime(new Date());
				product.setCardId(jo.getString("card_id"));
				productService.insert(product);
				return renderSuccess(product);
			} else {
				return renderError();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return renderError();
		} catch (WxErrorException e) {
			e.printStackTrace();
			return renderError();
		}
	}

	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(String cardId, HttpServletRequest request) {
		try {
			JSONObject jo = new JSONObject();
			jo.put("card_id", cardId);
			jo = JSONObject.parseObject(wxService.post(WxApi.card_delete, jo.toJSONString()));
			if (jo.getInteger("errcode") == 0) {
				productService.delete(new EntityWrapper<Product>().where("cardId={0}", cardId));
				return renderSuccess();
			} else {
				return renderError();
			}
		} catch (WxErrorException e) {
			e.printStackTrace();
			return renderError();
		}
	}

	@ResponseBody
	@RequestMapping("/qrcode")
	public JsonResult qrcode(String cardId, int count, HttpServletRequest request) {
		try {
			String filePath = ProductController.class.getResource("/qrcode.json").getPath();
			String json = FileUtils.readFileToString(new File(filePath));
			Map<String, String> args = new HashMap<String, String>();
			JSONArray ja = new JSONArray();
			for (int i = 0; i < count; i++) {
				JSONObject joo = new JSONObject();
				joo.put("card_id", cardId);
				joo.put("outer_str", "mokazu");
				ja.add(joo);
			}
			args.put("card_list", ja.toJSONString());
			json = Utils.replace(json, args);
			JSONObject jo = JSONObject.parseObject(json);
			jo = JSONObject.parseObject(wxService.post(WxApi.care_qrcode, jo.toJSONString()));
			if (jo.getInteger("errcode") == 0) {
				return renderSuccess(jo);
			} else {
				return renderError();
			}
		} catch (WxErrorException e) {
			e.printStackTrace();
			return renderError();
		} catch (IOException e) {
			e.printStackTrace();
			return renderError();
		}
	}
}
