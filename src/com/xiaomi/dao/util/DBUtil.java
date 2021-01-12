package com.xiaomi.dao.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBUtil {
	public static SqlSessionFactory factory;
	
	static {
		try {
			InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	
	//��ȡ����
	public static SqlSession getSqlSession() {
		return factory.openSession();
	}
	
}
