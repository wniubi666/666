package com.xiaomi.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xiaomi.dao.mapper.GoodMapper;
import com.xiaomi.dao.util.DBUtil;
import com.xiaomi.dao.vo.Good;
import com.xiaomi.dao.vo.GoodExample;
import com.xiaomi.dao.vo.GoodExample.Criteria;

public class GoodService {
	
	
	
	//1.��ѯ������Ʒ
	public List<Good> getAllGoods(){
		SqlSession sqlSession = DBUtil.getSqlSession();
		GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
		List<Good> goodList = mapper.selectByExample(null);
		sqlSession.close();
		return goodList;
	}
	
	//2.������Ʒ����ģ����ѯ��Ʒ
	public List<Good> getGoodsByName(String goodName){
		SqlSession sqlSession = DBUtil.getSqlSession();
		GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
		
		GoodExample example = new GoodExample();
		Criteria condition = example.createCriteria();
		condition.andGoodNameLike("%"+goodName+"%");
		List<Good> goodList = mapper.selectByExample(example);
		sqlSession.close();
		return goodList;
	}
	
	//3.������Ʒ���Ʋ�ѯ��Ʒ��Ϣ
	public Good getGoodByName(String goodName){
		SqlSession sqlSession = DBUtil.getSqlSession();
		GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
		
		GoodExample example = new GoodExample();
		Criteria condition = example.createCriteria();
		condition.andGoodNameEqualTo(goodName);
		List<Good> goodList = mapper.selectByExample(example);
		sqlSession.close();
		return goodList.get(0);
	}
	//4.����������ѯ��Ʒ��Ϣ
	public List<Good> getGoodsByCondition(String type,float price ,String goodName,String color) {
		SqlSession sqlSession = DBUtil.getSqlSession();
		GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
		GoodExample example = new GoodExample();
		Criteria condition = example.createCriteria();
		condition.andGoodTypeEqualTo(type);
		if(price!=0) {
			condition.andGoodPriceEqualTo(price);
		}
		condition.andGoodNameEqualTo(goodName);
		if(color!=null) {
			condition.andGoodColorEqualTo(color);
		}
		List<Good> selectByExample = mapper.selectByExample(example);
		sqlSession.close();
		return selectByExample;
	}

	public Good getGoodsByGid(int gId) {
		SqlSession sqlSession = DBUtil.getSqlSession();
		GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
		Good good = mapper.selectByPrimaryKey(gId);
		sqlSession.close();
		return good;
	}

}
