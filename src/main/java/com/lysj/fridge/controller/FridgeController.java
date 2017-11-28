package com.lysj.fridge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.soul.util.web.Resp;

 

@RestController
@RequestMapping("/fridge")
public class FridgeController {
	
	@GetMapping(value = "/list")
	public Resp list() {
		return new Resp("");
	}
	
	
}
