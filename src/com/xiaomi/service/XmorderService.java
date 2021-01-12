package com.xiaomi.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xiaomi.dao.mapper.XmorderMapper;
import com.xiaomi.dao.util.DBUtil;
import com.xiaomi.dao.vo.Xmorder;
import com.xiaomi.dao.vo.XmorderExample;
import com.xiaomi.dao.vo.XmorderExample.Criteria;

public class XmorderService {

	
	public List<Xmorder> selectOrdersByUid(int uid){
		SqlSession session= DBUtil.getSqlSession();
		XmorderMapper orderMapper = session.getMapper(XmorderMapper.class);
		XmorderExample example = new XmorderExample();
		Criteria condition = example.createCriteria();
		condition.andUidEqualTo(uid);
		List<Xmorder> orderList = orderMapper.selectByExample(example);
		session.close();
		return orderList;
	}

	public Xmorder selectOneOrder(int order_id) {
		SqlSession session= DBUtil.getSqlSession();
		XmorderMapper orderMapper = session.getMapper(XmorderMapper.class);
		Xmorder xmorder = orderMapper.selectByPrimaryKey(order_id);
		session.close();
		return xmorder;
	}

	public boolean addOrder(Xmorder xmorder) {
		SqlSession session= DBUtil.getSqlSession();
		XmorderMapper orderMapper = session.getMapper(XmorderMapper.class);
		int result = orderMapper.insertSelective(xmorder);
		session.commit();
		session.close();
		return result>0?true:false;
	}
	
}
