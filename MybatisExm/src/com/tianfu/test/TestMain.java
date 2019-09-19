package com.tianfu.test;

import java.lang.reflect.Proxy;

import com.tianfu.dao.BookDao;
import com.tianfu.dao.Dao;
import com.tianfu.dynamicAgent.BookInvoke;
import com.tianfu.entity.Book;

public class TestMain 
{
	private static Dao bookDao = null;
	private static void init()
	{
		Dao temDao = new BookDao();
		BookInvoke bookInvoke = new BookInvoke(temDao);
		
		bookDao = (Dao) Proxy.newProxyInstance(temDao.getClass().getClassLoader(), 
											temDao.getClass().getInterfaces(), 
												 bookInvoke);
	}
	
	public static void main(String[] args) 
	{
		init();
		Book book = (Book) bookDao.select();
		
		System.out.println(book);

	}
}
