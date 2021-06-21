package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryVo> getList(String id) {
		return categoryRepository.getList(id);
	}

	public List<CategoryVo> getAdminList(String id) {
		return categoryRepository.getAdminList(id);
	}

	public void addCategory(CategoryVo vo) {
		categoryRepository.addCategory(vo);
		
	}

	public Long getMinCategoryNo(String id) {
		return categoryRepository.getMinCategoryNo(id);
	}

	public void delete(Long no) {
		categoryRepository.delete(no);
		
	}

}
