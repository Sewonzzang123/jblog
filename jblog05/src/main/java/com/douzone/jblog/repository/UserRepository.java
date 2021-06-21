package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public UserVo login(String id, String password) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("password", password);		
		return sqlSession.selectOne("user.getUserByIdAndPassword",map);		
	}

	public void join(UserVo vo) {
		sqlSession.insert("user.join", vo);
		
	}

	public UserVo getUser(String id) {
		return sqlSession.selectOne("user.getUserById",id);
	}
}
