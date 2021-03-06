package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	 
	@Autowired
	private BlogService blogService;
	@Autowired
	private PostService postService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping({ "", "/{categoryNo}", "/{categoryNo}/{postNo}" })
	public String index(
		@PathVariable("id") String id,
		@PathVariable("categoryNo") Optional<Long> pathNo1,
		@PathVariable("postNo") Optional<Long> pathNo2,
		Model model) {
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if(pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		} else {
			categoryNo = categoryService.getMinCategoryNo(id);
		}
		
		PostVo post = postService.getPost(categoryNo, postNo);
		model.addAttribute("post", post);
		
		//????????? ?????? ????????????
		BlogVo vo = blogService.getBlog(id);
		model.addAttribute("blog", vo);
		//????????? ?????? ????????????
		List<PostVo> postList = postService.getList(id,categoryNo);
		model.addAttribute("postList", postList);
		//???????????? ?????? ????????????
		List<CategoryVo> categoryList = categoryService.getList(id);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryNo", categoryNo);
		
		return "blog/main";
	}
	
	@Auth
	@RequestMapping(value = "/admin/basic", method=RequestMethod.GET)
	public String adminBasic(@PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.getBlog(id);
		model.addAttribute("blog", vo);
		
		return "blog/admin/basic";	
	}
	@Auth
	@RequestMapping(value="/admin/basic", method=RequestMethod.POST)
	public String adminBasicUpdate(@PathVariable("id") String id,
			BlogVo blogVo,
			@RequestParam("logoFile") MultipartFile profile) {
		//??????
		String url = fileUploadService.restore(profile);
		blogVo.setLogo(url);
		blogVo.setId(id);
		
		blogService.update(blogVo);
		return "redirect:/"+id+"/admin/basic";
	}
	@Auth
	@RequestMapping(value="/admin/category", method=RequestMethod.GET)
	public String adminCategory(@PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.getBlog(id);
		model.addAttribute("blog", vo);	
		return "blog/admin/category";
	}
	
	@Auth
	@ResponseBody
	@RequestMapping(value="/admin/category/api/list")
	public JsonResult getList(@PathVariable("id") String id) {
		List<CategoryVo> list = categoryService.getAdminList(id);		
		return JsonResult.success(list);
	}
	
	@Auth
	@ResponseBody
	@RequestMapping(value="/admin/category/api/add")
	public JsonResult addCategory(@PathVariable("id") String id, @RequestBody CategoryVo vo) {
		vo.setUserId(id);
		categoryService.addCategory(vo);
		vo.setTotalPost(0);
		return JsonResult.success(vo);
	}
	
	@Auth
	@ResponseBody
	@RequestMapping(value="/admin/category/api/delete/{no}")
	public JsonResult deleteCategory(@PathVariable("id") String id,
		@PathVariable("no") Long no) {
		Boolean result = categoryService.delete(no);
		return JsonResult.success(result);
	}
	
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.GET)
	public String writeForm(@PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.getBlog(id);
		model.addAttribute("blog", vo);
		
		List<CategoryVo> list = categoryService.getList(id);
		model.addAttribute("list", list);
		return "blog/admin/write";
	}
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String write(@PathVariable("id") String id, PostVo vo) {
		Long no = postService.addPost(vo);
		return "redirect:/{id}/"+vo.getCategoryNo()+"/"+no;
	}
}