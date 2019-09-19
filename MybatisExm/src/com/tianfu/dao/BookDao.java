package com.tianfu.dao;
import org.apache.ibatis.session.SqlSession;

public class BookDao implements Dao
{
	private SqlSession session;
	public BookDao() 
	{
		session = null;
	}
	public BookDao(SqlSession sess) 
	{
		this.session = sess;
	}
	
	public SqlSession getSession() {
		return session;
	}

	public void setSession(SqlSession session) {
		this.session = session;
	}

	public Object select()
	{
		//核心代码
		return session.selectOne("com.tianfu.entity.book-mapper.findBookById",1001);
	}
}
