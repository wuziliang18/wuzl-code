package org.wuzl.learn.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.wuzl.learn.mybatis.bean.User;

import com.alibaba.fastjson.JSON;

public class Test {
	private static long a;
	public static void main(String[] args) throws IOException {
		a++;
		System.out.println(a);
		Reader reader = Resources.getResourceAsReader("Configuration.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(reader);
		SqlSession session = sqlSessionFactory.openSession();
		User user = session.selectOne("org.wuzl.learn.mybatis.user.getByPK", 1l);
		outJson(user);
	}

	public static void outJson(Object obj) {
		System.out.println(JSON.toJSON(obj));
	}
}
