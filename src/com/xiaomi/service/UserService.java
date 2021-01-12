package com.xiaomi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.xiaomi.dao.mapper.UsersMapper;
import com.xiaomi.dao.util.DBUtil;
import com.xiaomi.dao.vo.Users;
import com.xiaomi.dao.vo.UsersExample;
import com.xiaomi.dao.vo.UsersExample.Criteria;

public class UserService {
	
	//注册方法
	public boolean register(Users user,HttpServletRequest request) {
		boolean flag =  false;
		
		SqlSession sqlSession = DBUtil.getSqlSession();
		UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
		//判断账号是否存在
		UsersExample example = new UsersExample();
		//创造查询条件
		Criteria conditioin = example.createCriteria();
		conditioin.andUsernameEqualTo(user.getUsername());
		List<Users> userList1 = mapper.selectByExample(example);
		if(userList1.size()>0) {
			flag = false;
			request.setAttribute("rmsg", "用户已经存在！");
		}else {
			//执行添加功能
			mapper.insertSelective(user);
			sqlSession.commit();
			flag = true;
		}
		
		sqlSession.close();
		return flag;
	}
	
	
	//验证码校验
	public boolean imgCodeValidate(String codeImg,HttpServletRequest request) {
		boolean flag =  true;
		
		String code = (String) request.getSession().getAttribute("code");
		if(!code.equals(codeImg)) {
			//提示用户验证码错误
			request.setAttribute("rmsg", "验证码输入错误！！");
			flag = false; 
		}
		
		return flag;
	}
	
	//登录校验
	public boolean login(String username,String password,HttpServletRequest request) {
		SqlSession sqlSession = DBUtil.getSqlSession();
		UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
		UsersExample example = new UsersExample();
		Criteria condition = example.createCriteria();
		condition.andUsernameEqualTo(username);
		condition.andPasswordEqualTo(password);
		List<Users> user = usersMapper.selectByExample(example);
		if(user.size()==0) {
			request.setAttribute("rmsg", "账号密码错误！");
		}
		request.getSession().setAttribute("user", user.get(0));
		sqlSession.close();
		return user.size()>0?true:false;
	}
	
	//更新用户信息
	public boolean updateUser(Users user) {
		SqlSession sqlSession = DBUtil.getSqlSession();
		UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
		int count = usersMapper.updateByPrimaryKeySelective(user);
		sqlSession.commit();
		sqlSession.close();
		return count>0?true:false;
	}
}
