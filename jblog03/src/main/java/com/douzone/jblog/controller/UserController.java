package com.douzone.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginForm() {
		return "user/login";
	}

	@RequestMapping(value="join",method=RequestMethod.GET)
	public String joinForm(@ModelAttribute UserVo userVo, BindingResult result, Model model) {
		return "user/join";
	}
	
	@RequestMapping(value="join",method=RequestMethod.POST)
	public String join(@Valid @ModelAttribute UserVo userVo, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		userService.join(userVo);
		return "user/joinsuccess";
	}
	
}
