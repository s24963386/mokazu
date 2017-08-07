package com.mokazu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.mokazu.common.JsonResult;
import com.mokazu.utils.Md5Util;

/**
 * 用户接入
 * 
 * @author jesen.shen
 *
 */
@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {

	@RequestMapping("/login")
	public String login(@RequestParam(value = "username", defaultValue = "") String username, @RequestParam(value = "password", defaultValue = "") String pwd, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JsonResult jr = plogin(request, response, username, pwd);
		if (jr.isSuccess()) {
			return "redirect:/mk/home#" + request.getContextPath() + "/mk/order";
		} else {
			return "back/login";
		}
	}

	@RequestMapping("/login_browser")
	public String loginBrowser(@RequestParam(value = "username", defaultValue = "") String username, @RequestParam(value = "password", defaultValue = "") String pwd, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return login(username, Md5Util.md5(pwd), request, response);
	}

	@ResponseBody
	@RequestMapping("/login_client")
	public JsonResult loginClient(@RequestParam(value = "username", defaultValue = "") String username, @RequestParam(value = "password", defaultValue = "") String pwd, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		pwd = Md5Util.md5(pwd);
		return plogin(request, response, username, pwd);
	}

	private JsonResult plogin(HttpServletRequest request, HttpServletResponse response, String username, String pwd) {
		if (StringUtils.isEmpty(username)) {
			return renderError();
		} else {
			UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
			token.setRememberMe(true);
			Subject subject = SecurityUtils.getSubject();
			String msg = "";
			try {
				subject.login(token);
				if (subject.isAuthenticated()) {
					setCookie(response, USERNAME, username, 24 * 3600 * 180, "/", null);
					return renderSuccess();
				} else {
					return renderError();
				}
			} catch (IncorrectCredentialsException e) {
				msg = "Password for account " + token.getPrincipal() + " was incorrect.";
			} catch (ExcessiveAttemptsException e) {
				msg = "Excessive Attempts"; // 登录次数过多
			} catch (LockedAccountException e) {
				msg = "The account for username " + token.getPrincipal() + " was locked.";
			} catch (DisabledAccountException e) {
				msg = "The account for username " + token.getPrincipal() + " was disabled.";
			} catch (ExpiredCredentialsException e) {
				msg = "the account for username " + token.getPrincipal() + "  was expired.";
			} catch (UnknownAccountException e) {
				msg = " There is no user with username of " + token.getPrincipal();
			} catch (UnauthorizedException e) {
				msg = "Unauthorized." + e.getMessage();
			}
			request.setAttribute("error", msg);
			return renderError(msg);
		}
	}

	@RequestMapping("/unauth")
	public String unauth() {
		return "unauth";
	}
}
