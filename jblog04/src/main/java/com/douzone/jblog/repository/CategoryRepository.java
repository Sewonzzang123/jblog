package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;

	public void join(String id) {
		sqlSession.insert("category.join", id);
		
	}

	public List<CategoryVo> getList(String id) {
		return sqlSession.selectList("category.getList", id);
	}

	public List<CategoryVo> getAdminList(String id) {
		return sqlSession.selectList("category.getAdminList", id);
	}

	public void addCategory(CategoryVo vo) {
		sqlSession.insert("category.addCategory", vo);
	}

	public Long getMinCategoryNo(String id) {
		return sqlSession.selectOne("category.getMinCategoryNo", id);
	}

	public void delete(Long no) {
		sqlSession.delete("category.delete", no);
		
	}
}
