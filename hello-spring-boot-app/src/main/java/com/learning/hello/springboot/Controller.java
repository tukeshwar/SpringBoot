package com.learning.hello.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class Controller {
	@RequestMapping("/hello")
	public String requestMethodName(@RequestParam String name) {
		return "Hello" + (name==null || name.isEmpty() ? "" : name);
	}
	
}
