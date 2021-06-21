package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;

	public void join(String id) {
		sqlSession.insert("blog.join", id);
		
	}

	public BlogVo getBlog(String id) {
		return sqlSession.selectOne("blog.getBlogById", id);
	}

	public void update(BlogVo blogVo) {
		sqlSession.update("blog.update", blogVo);
		
	}

	public List<BlogVo> search(String keyword, String which) {
		Map<String, String> map = new HashMap<>();
		map.put("keyword", keyword);
		map.put("which", which);
		return sqlSession.selectList("blog.search", map);
	}
}
