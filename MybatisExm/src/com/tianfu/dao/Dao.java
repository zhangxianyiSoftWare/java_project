package com.tianfu.dao;

import org.apache.ibatis.session.SqlSession;

public interface Dao 
{
	public Object select();
	public SqlSession getSession();
	public void setSession(SqlSession sess);
		
}
