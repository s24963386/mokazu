package com.mokazu.shiro;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mokazu.entity.User;
import com.mokazu.exception.CustomAuthenticationException;
import com.mokazu.service.IUserService;

public class IAMRealm implements Realm {

	private static final Logger	logger	= LogManager.getLogger(IAMRealm.class);

	@Autowired
	private IUserService		userService;

	@Override
	public String getName() {
		return "IAMRealm";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		// 仅支持UsernamePasswordToken类型的Token
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal(); // 得到用户名
		String password = new String((char[]) token.getCredentials()); // 得到密码
		try {
			if (!iamAuth(username, password)) {
				throw new IncorrectCredentialsException(); // 如果密码错误
			} else {
				// 如果身份认证验证成功，返回一个AuthenticationInfo实现；
				return new SimpleAuthenticationInfo(username, password, getName());
			}
		} catch (IOException e) {
			logger.error(e);
			throw new CustomAuthenticationException(e.getMessage());
		}
	}

	private boolean iamAuth(String username, String password) throws IOException {
		User user = userService.selectOne(new EntityWrapper<User>().where("username={0}", username));
		if (user != null && StringUtils.equalsIgnoreCase(password, user.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
}
