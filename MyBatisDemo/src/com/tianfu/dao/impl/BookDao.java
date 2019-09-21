package com.tianfu.dao.impl;
import org.apache.ibatis.session.SqlSession;

import com.tianfu.dao.Dao;
import com.tianfu.entity.Book;

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
	
	@Override
	public Object selectOne(Object obj)
	{
		Book tmpBook = (Book)obj;
		if(tmpBook == null || tmpBook.getId() == 0)
		{
			return null;
		}
		//System.out.println("book id"+tmpBook.getId());
		//核心代码
		return session.selectOne("com.tianfu.entity.book-mapper.findBookById",tmpBook.getId());
	}
	
	@Override
	public Object addOne(Object obj)
	{
		Book tmpBook = (Book)obj;
		if(tmpBook == null || tmpBook.getId() == 0)
		{
			return null;
		}
		//System.out.println("book id"+tmpBook.getId());
		//核心代码
		int rows = session.insert("com.tianfu.entity.book-mapper.addBook",tmpBook);
		session.commit();
		
		return rows;
	}
	@Override
	public Object updateOne(Object obj) 
	{
		// TODO Auto-generated method stub
		Book tmpBook = (Book)obj;
		if(tmpBook == null || tmpBook.getId() == 0)
		{
			return null;
		}
		//System.out.println("book id"+tmpBook.getId());
		//核心代码
		int rows = session.update("com.tianfu.entity.book-mapper.updateBook", tmpBook);
		session.commit();
		
		return rows;
	}
	@Override
	public Object deletOne(Object obj) 
	{
		// TODO Auto-generated method stub
		Book tmpBook = (Book)obj;
		if(tmpBook == null || tmpBook.getId() == 0)
		{
			return null;
		}
		//System.out.println("book id"+tmpBook.getId());
		//核心代码
		int rows = session.delete("com.tianfu.entity.book-mapper.deleteBook", tmpBook.getId());
		session.commit();
		return rows;
	}
}
