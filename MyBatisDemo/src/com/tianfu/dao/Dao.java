package com.tianfu.dao;

import org.apache.ibatis.session.SqlSession;


public interface Dao 
{
	public Object selectOne(Object obj);
	public Object addOne(Object obj);
	public Object updateOne(Object obj);
	public Object deletOne(Object obj);
	
	public SqlSession getSession();
	public void setSession(SqlSession sess);
		
}
