package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public List<PostVo> getList(String id, Long categoryNo) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("categoryNo", String.valueOf(categoryNo));
		return sqlSession.selectList("post.getList", map);
	}

	public Long addPost(PostVo vo) {
		sqlSession.insert("post.addPost", vo);
		return vo.getNo();
	}

	public PostVo getLeastPost(Long categoryNo,Long postNo) {
		Map<String, Long> map = new HashMap<>();
		map.put("categoryNo", categoryNo);
		map.put("postNo", postNo);
		return sqlSession.selectOne("post.getPost", map);
	}
}
