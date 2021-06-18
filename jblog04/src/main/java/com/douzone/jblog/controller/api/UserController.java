package com.douzone.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@RestController("userControllerApi")
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/checkid")
	public JsonResult checkId(@RequestParam(value="id", required = true, defaultValue = "")String id) {
		System.out.println("여기옴");
		UserVo userVo = userService.getUser(id);
		boolean data = (userVo != null);

//		JsonResult result = JsonResult.success(data); >>문제없이 checkemail을 하였으므로 success
//		JsonResult result2 = JsonResult.fail("...."); >>globalExceptionHandler에서 fail을 준다.
		
		return JsonResult.success(data);
	}
}