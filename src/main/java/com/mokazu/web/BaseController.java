package com.mokazu.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mokazu.common.JsonResult;

public class BaseController {

	private static final Logger	baseLogger		= LogManager.getLogger(BaseController.class);

	public static final String	USERNAME		= "ip_username";

	public static final String	COMPANYID		= "companyid";

	public static final String	ROLEID			= "roleid";

	public static final String	ROLE_SUMSCOPE	= "sumscope";

	public static final String	COMPANYAUTHZ	= "companyauthz";

	/**
	 * 渲染失败数据
	 *
	 * @return result
	 */
	protected JsonResult renderError() {
		JsonResult result = new JsonResult();
		result.setSuccess(false);
		result.setStatus("500");
		return result;
	}

	/**
	 * 渲染失败数据（带消息）
	 *
	 * @param msg
	 *            需要返回的消息
	 * @return result
	 */
	protected JsonResult renderError(String msg) {
		JsonResult result = renderError();
		result.setMsg(msg);
		return result;
	}

	/**
	 * 渲染成功数据
	 *
	 * @return result
	 */
	protected JsonResult renderSuccess() {
		JsonResult result = new JsonResult();
		result.setSuccess(true);
		result.setStatus("200");
		return result;
	}

	/**
	 * 渲染成功数据（带信息）
	 *
	 * @param msg
	 *            需要返回的信息
	 * @return result
	 */
	protected JsonResult renderSuccess(String msg) {
		JsonResult result = renderSuccess();
		result.setMsg(msg);
		return result;
	}

	/**
	 * 渲染成功数据（带数据）
	 *
	 * @param obj
	 *            需要返回的对象
	 * @return result
	 */
	protected JsonResult renderSuccess(Object obj) {
		JsonResult result = renderSuccess();
		result.setObj(obj);
		return result;
	}

	/**
	 * 渲染成功数据
	 *
	 * @param obj
	 *            需要返回的对象
	 * @return result
	 */
	protected JsonResult renderResult(boolean isSuccess) {
		return isSuccess ? renderSuccess() : renderError();
	}

	/**
	 * 渲染成功数据
	 *
	 * @param obj
	 *            需要返回的对象
	 * @return result
	 */
	protected JsonResult renderResult(boolean isSuccess, Object obj, String msg) {
		return isSuccess ? renderSuccess(obj) : renderError(msg);
	}

	/**
	 * 获取当前接入的用户名
	 * 
	 * @param request
	 * @return
	 */
	protected String getUsername(HttpServletRequest request) {
		String ret = (String) request.getSession().getAttribute(USERNAME);
		if (StringUtils.isEmpty(ret)) {
			ret = request.getParameter("username");
			baseLogger.info("getUsername from parameter:{}", ret);
		}
		if (StringUtils.isEmpty(ret)) {
			ret = getCookie(request, USERNAME, null);
			baseLogger.info("getUsername from cookie:{}", ret);
		}
		return ret;
	}

	protected String getCompanyId(HttpServletRequest request) {
		if (request.getSession().getAttribute(COMPANYID) != null) {
			return (String) request.getSession().getAttribute(COMPANYID);
		} else {
			baseLogger.info("{}  getCompanyId  is null===================", getUsername(request));
			return null;
		}

	}

	protected boolean getCompanyAuthz(HttpServletRequest request) {
		if (request.getSession().getAttribute(COMPANYAUTHZ) != null) {
			return (boolean) request.getSession().getAttribute(COMPANYAUTHZ);
		} else {
			baseLogger.info("{}  getCompanyAuthz  is null===================", getUsername(request));
			return false;
		}
	}

	protected String getRoleId(HttpServletRequest request) {
		if (request.getSession().getAttribute(ROLEID) != null) {
			return (String) request.getSession().getAttribute(ROLEID);
		} else {
			baseLogger.info("{}  getRoleId  is null===================", getUsername(request));
			return null;
		}
	}

	/**
	 * 获取cookie
	 * 
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	protected String getCookie(HttpServletRequest request, String name, String defaultValue) {
		Cookie cookie = getCookieObject(request, name);
		return cookie != null ? cookie.getValue() : defaultValue;
	}

	/**
	 * 获取cookie
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	protected Cookie getCookieObject(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies)
				if (cookie.getName().equals(name))
					return cookie;
		return null;
	}

	/**
	 * 设置cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAgeInSeconds
	 * @param path
	 * @param domain
	 * @return
	 */
	protected boolean setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds, String path, String domain) {
		Cookie cookie = new Cookie(name, value);
		if (domain != null)
			cookie.setDomain(domain);
		cookie.setMaxAge(maxAgeInSeconds);
		cookie.setPath(path);
		response.addCookie(cookie);
		return true;
	}
}
