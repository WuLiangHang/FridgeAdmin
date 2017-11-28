package com.lysj.fridge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lysj.base.BaseContent;
import com.lysj.fridge.domain.Privilege;
import com.lysj.fridge.domain.Staff;

import cn.soul.util.web.ParamUtil;
import cn.soul.util.web.Resp;

@RestController
@RequestMapping("/staff")
public class StaffController extends BaseContent {
	// 基本接口///////////////////////////////////////
	@PostMapping(value = "/login")
	public Resp login(Staff model) {
		if (ParamUtil.isBlack(model.getUsername()) || ParamUtil.isBlack(model.getPassword())) {
			return new Resp(Resp.PARAM_ERROR, "账号或者密码为空");
		}
		model.setLoginIp(request.getRemoteAddr());
		model.setLoginDate(System.currentTimeMillis());

		Staff staff = staffRepository.findByUsername(model.getUsername());
		if (staff == null || !staff.getPassword().equals(ParamUtil.md5(model.getPassword()))) {
			return new Resp(Resp.PARAM_ERROR, "账号或者密码错误");
		}
		if (staff.getExpires() < System.currentTimeMillis()) {// token不在有效期内,更新token
			staff.setToken(ParamUtil.uuid());
		}
		staff.setExpires(System.currentTimeMillis() + LOGIN_EXPIRES_STAFF);//
		staff.setLoginIp(model.getLoginIp());
		staff.setLoginDate(model.getLoginDate());
		staffRepository.save(staff);

		return new Resp(staff);
	}

	@GetMapping(value = "/isLogin")
	public Resp isLogin(String token) {
		Staff staff = staffRepository.findByToken(token);
		if (staff == null) {
			return new Resp(Resp.TOKEN_ERROR, "登录信息已经失效,请重新登录");
		}
		if (staff.getExpires() < System.currentTimeMillis()) {
			return new Resp(Resp.TOKEN_ERROR, "登录信息已经失效,请重新登录");
		}
		staff.setExpires(System.currentTimeMillis() + LOGIN_EXPIRES_STAFF);
		staffRepository.save(staff);
		return new Resp(staff);
	}

	@GetMapping(value = "/quit")
	public Resp quit(String token) {
		Staff staff = staffRepository.findByToken(token);
		if (staff != null) {
			staff.setExpires(System.currentTimeMillis());
		}
		return new Resp("退出成功!");
	}

	@GetMapping(value = "/myPriv")
	public Resp myPriv(String token) {
		List<Privilege> privileges = privilegeRepository.findAll();

		return new Resp(privileges);
	}

}
