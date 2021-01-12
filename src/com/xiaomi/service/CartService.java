package com.xiaomi.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xiaomi.dao.mapper.CartMapper;
import com.xiaomi.dao.util.DBUtil;
import com.xiaomi.dao.vo.Cart;
import com.xiaomi.dao.vo.CartExample;
import com.xiaomi.dao.vo.CartExample.Criteria;

public class CartService {
	
	//��ӹ��ﳵ����
	public boolean addCart(Cart cart) {
		SqlSession sqlSession = DBUtil.getSqlSession();
		CartMapper mapper = sqlSession.getMapper(CartMapper.class);
		int result = mapper.insertSelective(cart);
		sqlSession.commit();
		sqlSession.close();
		return result>0?true:false;
	}
	
	//��ѯ�û���ӵ���Ʒ�������û�ID����ƷID��
	public Cart findCartByUidGid(int uid,int gid) {
		SqlSession sqlSession = DBUtil.getSqlSession();
		CartMapper mapper = sqlSession.getMapper(CartMapper.class);
		
		CartExample example = new CartExample();
		Criteria condition = example.createCriteria();
		if(gid!=0) {
			condition.andGoodIdEqualTo(gid);
		}
		condition.andUidEqualTo(uid);
		List<Cart> selectByExample = mapper.selectByExample(example);
		Cart result = null;
		if(selectByExample.size()>0) {
			result =  selectByExample.get(0);
		}
		sqlSession.close();
		return result;
	}
	
	//��ѯ�û�ID��ѯ���ﳵ
	public List<Cart> findCartByUid(int uid) {
			SqlSession sqlSession = DBUtil.getSqlSession();
			CartMapper mapper = sqlSession.getMapper(CartMapper.class);
			
			CartExample example = new CartExample();
			Criteria condition = example.createCriteria();
			condition.andUidEqualTo(uid);
			List<Cart> selectByExample = mapper.selectByExample(example);
			sqlSession.close();
			return selectByExample;
	}
	
	
	//���¹��ﳵ��Ϣ
	public boolean updateCart(Cart cart) {
		SqlSession sqlSession = DBUtil.getSqlSession();
		CartMapper mapper = sqlSession.getMapper(CartMapper.class);
		CartExample example = new CartExample();
		Criteria condition = example.createCriteria();
		condition.andPreIdEqualTo(cart.getPreId());
		int result = mapper.updateByExampleSelective(cart, example);
		sqlSession.commit();
		sqlSession.close();
		return result>0?true:false;
	}

	public boolean deleteCartByPreId(String preId) {
		SqlSession sqlSession = DBUtil.getSqlSession();
		CartMapper mapper = sqlSession.getMapper(CartMapper.class);
		int result = mapper.deleteByPrimaryKey(Integer.valueOf(preId));
		sqlSession.commit();
		sqlSession.close();
		return result>0?true:false;
	}

	public Cart selectOneCart(int cartid1) {
		SqlSession sqlSession = DBUtil.getSqlSession();
		CartMapper mapper = sqlSession.getMapper(CartMapper.class);
		Cart cart = mapper.selectByPrimaryKey(cartid1);
		return cart;
	}
}
