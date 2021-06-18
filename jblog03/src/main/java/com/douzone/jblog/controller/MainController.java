package com.douzone.jblog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;

@Controller
public class MainController {
	@Autowired
	private BlogService blogService;

	@RequestMapping(value = { "", "/search" })
	public String index(HttpServletRequest request, Model model) {
		String keyword = request.getParameter("keyword");
		String which= "";
		if (!"".equals(keyword)) {
			which = request.getParameter("which");
			List<BlogVo> list = blogService.search(keyword, which);
			for (BlogVo vo : list) {
				System.out.println(vo);
			}
			model.addAttribute("list", list);
		}
			return "main/index";
		}


}
