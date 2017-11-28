package com.lysj.fridge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lysj.base.BaseContent;
import com.lysj.fridge.domain.User;

import cn.soul.util.web.ParamUtil;
import cn.soul.util.web.Resp;

@RestController
@RequestMapping("/user")
public class UserController extends BaseContent {

	
	@GetMapping(value = "/isLogin")
	public Resp isLogin(String token) {
		if (ParamUtil.isBlack(token)) {
			return new Resp(Resp.TOKEN_ERROR, "登录信息已经失效,请重新登录");
		}
		User user = userRepository.findByToken(token);
		if (user == null) {
			return new Resp(Resp.TOKEN_ERROR, "登录信息已经失效,请重新登录");
		}
		if (user.getExpires() < System.currentTimeMillis()) {
			return new Resp(Resp.TOKEN_ERROR, "登录信息已经失效,请重新登录");
		}
		user.setExpires(System.currentTimeMillis() + LOGIN_EXPIRES_USER);// 登录有效期
		userRepository.save(user);
		return new Resp(user);
	}

}