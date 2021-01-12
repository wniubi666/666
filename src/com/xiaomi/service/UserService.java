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
	
	//ע�᷽��
	public boolean register(Users user,HttpServletRequest request) {
		boolean flag =  false;
		
		SqlSession sqlSession = DBUtil.getSqlSession();
		UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
		//�ж��˺��Ƿ����
		UsersExample example = new UsersExample();
		//�����ѯ����
		Criteria conditioin = example.createCriteria();
		conditioin.andUsernameEqualTo(user.getUsername());
		List<Users> userList1 = mapper.selectByExample(example);
		if(userList1.size()>0) {
			flag = false;
			request.setAttribute("rmsg", "�û��Ѿ����ڣ�");
		}else {
			//ִ����ӹ���
			mapper.insertSelective(user);
			sqlSession.commit();
			flag = true;
		}
		
		sqlSession.close();
		return flag;
	}
	
	
	//��֤��У��
	public boolean imgCodeValidate(String codeImg,HttpServletRequest request) {
		boolean flag =  true;
		
		String code = (String) request.getSession().getAttribute("code");
		if(!code.equals(codeImg)) {
			//��ʾ�û���֤�����
			request.setAttribute("rmsg", "��֤��������󣡣�");
			flag = false; 
		}
		
		return flag;
	}
	
	//��¼У��
	public boolean login(String username,String password,HttpServletRequest request) {
		SqlSession sqlSession = DBUtil.getSqlSession();
		UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
		UsersExample example = new UsersExample();
		Criteria condition = example.createCriteria();
		condition.andUsernameEqualTo(username);
		condition.andPasswordEqualTo(password);
		List<Users> user = usersMapper.selectByExample(example);
		if(user.size()==0) {
			request.setAttribute("rmsg", "�˺��������");
		}
		request.getSession().setAttribute("user", user.get(0));
		sqlSession.close();
		return user.size()>0?true:false;
	}
	
	//�����û���Ϣ
	public boolean updateUser(Users user) {
		SqlSession sqlSession = DBUtil.getSqlSession();
		UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
		int count = usersMapper.updateByPrimaryKeySelective(user);
		sqlSession.commit();
		sqlSession.close();
		return count>0?true:false;
	}
}
