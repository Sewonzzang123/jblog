package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;

	public List<PostVo> getList(String id, Long categoryNo) {
		return postRepository.getList(id,categoryNo);
	}

	public Long addPost(PostVo vo) {
		return postRepository.addPost(vo);
	
	}

	public PostVo getPost(Long categoryNo, Long postNo) {
		return postRepository.getLeastPost(categoryNo,postNo);
	}

	
}
