package com.learning.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UiHandler {
	
	@GetMapping("/index")
	public String index(@RequestParam(required = false) String param) {
		return "index";
	}
	

}
