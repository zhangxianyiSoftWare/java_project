package com.tianfu.utils;

import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.tianfu.dao.Dao;
import com.tianfu.entity.Book;

public class BookInvoke implements InvocationHandler {
	
	private Dao target;
	
	//关联对象
	public BookInvoke(Dao target) 
	{
		// TODO Auto-generated constructor stub
	    this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		// 读取配置文件
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		//根据配置文件创建会话工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		//创建会话
		SqlSession session = sqlSessionFactory.openSession();
		if(target.getSession() == null)
		{
			target.setSession(session);
		}
		//方法执行
		Object obj = method.invoke(target, args);
		session.close();
		target.setSession(null);
		return obj;
	}

}
