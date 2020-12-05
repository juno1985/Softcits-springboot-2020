package org.softcits.cn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public ModelAndView hello() {
	
		return new ModelAndView("/page/Hello.html");
	}

}
