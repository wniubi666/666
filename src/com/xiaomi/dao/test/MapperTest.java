package com.xiaomi.dao.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.xiaomi.dao.mapper.UsersMapper;
import com.xiaomi.dao.util.DBUtil;
import com.xiaomi.dao.vo.Users;

public class MapperTest {
	
	@Test
	public void testQuery() {
		SqlSession sqlSession = DBUtil.getSqlSession();
		UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
		List<Users> userList = mapper.selectByExample(null);
		for(Users user : userList) {
			System.out.println(user);
		}
		sqlSession.close();
	}
	
	@Test
	public void testInsert() {
		SqlSession sqlSession = DBUtil.getSqlSession();
		UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
		Users user = new Users(999,"test","test","18874382587","ºşÄÏ³¤É³","³ª¸èÌøÎè","test");
		mapper.insertSelective(user);
		sqlSession.commit();
		sqlSession.close();
	}

}
