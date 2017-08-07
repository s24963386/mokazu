package com.mokazu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mk")
public class MkController extends BaseController {

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/home")
	public String home() {
		return "back/index";
	}

	@RequestMapping("/order")
	public String order() {
		return "back/order";
	}

	@RequestMapping("/product")
	public String product() {
		return "back/product";
	}

}
