package com.lysj.fridge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lysj.base.BaseContent;
import com.lysj.fridge.domain.User;

import cn.soul.util.web.ParamUtil;
import cn.soul.util.web.Resp;

@RestController
@RequestMapping("/userManager")
public class UserManageController extends BaseContent {

	@GetMapping(value = "/list")
	public Resp list() {
		return new Resp(userRepository.findAll());
	}

	@PostMapping(value = "/add")
	public Resp add(User model) {
		if (ParamUtil.isBlack(model.getPhone())) {
			return new Resp(Resp.PARAM_ERROR, "请输入手机号码");
		}
		if (ParamUtil.isBlack(model.getUsername())) {
			return new Resp(Resp.PARAM_ERROR, "请输入用户名");
		}

		model.setStatus(1);
		userRepository.save(model);
		return new Resp("添加成功!");
	}

}