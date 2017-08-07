package com.mokazu.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;

public enum ExpressUtils {

	insance;

	private Logger			logger	= LogManager.getLogger(ExpressUtils.class);

	private ExpressRunner	runner;

	private ExpressUtils() {
		runner = new ExpressRunner();
		try {
			runner.addOperatorWithAlias("属于", "in", null);
			runner.addOperatorWithAlias("等于", "==", null);
			runner.addOperatorWithAlias("大于", ">", null);
			runner.addOperatorWithAlias("小于", "<", null);
			runner.addOperatorWithAlias("而且", "and", null);
			runner.addOperatorWithAlias("或者", "or", null);
			runner.addOperatorWithAlias("如果", "if", null);
			runner.addOperatorWithAlias("则", "then", null);
			runner.addOperatorWithAlias("否则", "else", null);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public boolean check(String express, Map<String, Object> params) {
		if (StringUtils.isEmpty(express)) {
			return false;
		} else {
			IExpressContext<String, Object> expressContext = new DefaultContext<String, Object>();
			if (params != null) {
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					expressContext.put(entry.getKey(), entry.getValue());
				}
			}
			try {
				return (Boolean) runner.execute(express, expressContext, null, true, false);
			} catch (Exception e) {
				logger.error(e);
				return false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", "其他1");
		params.put("start", 1);
		params.put("end", 100);
		// String express = "if(unit=='YD'){return term>2} else return false;";
		String express = "type in['其他','sd']";
		System.out.println(insance.check(express, params));
	}
}
