package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	public UserVo login(String id, String password) {
		return userRepository.login(id,password);
	}
	
	public int join(UserVo vo) {
		userRepository.join(vo);
		blogRepository.join(vo.getId());
		categoryRepository.join(vo.getId());
		return 0;
	}

	public UserVo getUser(String id) {
		return userRepository.getUser(id);
	}
	
}
