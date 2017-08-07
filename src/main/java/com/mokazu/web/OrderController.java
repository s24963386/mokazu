package com.mokazu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.Condition;
import com.mokazu.common.JsonResult;
import com.mokazu.service.IOrderService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jesen.shen
 * @since 2017-07-27
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

	@Autowired
	private IOrderService	orderService;

	@ResponseBody
	@RequestMapping("/list")
	public JsonResult list() {
		return renderSuccess(orderService.selectList(Condition.Empty()));
	}

}
