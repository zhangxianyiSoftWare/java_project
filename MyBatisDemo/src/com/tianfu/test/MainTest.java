package com.tianfu.test;

import java.lang.reflect.Proxy;

import com.tianfu.dao.impl.BookDao;
import com.tianfu.dao.Dao;
import com.tianfu.utils.BookInvoke;
import com.tianfu.entity.Book;

public class MainTest 
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
	
	public static void test_insert()
	{
		Book book2 = new Book();
		book2.setId(1007);
		book2.setCategory("金融");
		book2.setDescription("desc");
		book2.setName("论经济");
		book2.setPnum(3);
		book2.setPrice(55);
		
		int rows = (Integer) bookDao.addOne(book2);
		System.out.println("insert into "+rows+"rows");
	}
	
	public static void test_update()
	{
		//这里似乎有些问题 比如 如果字段为空 按照插入语句 岂不是会将原有数据覆盖？
		Book book2 = new Book();
		book2.setId(1007);
		book2.setName("论经济");
		book2.setPnum(3);
		book2.setPrice(55);
		
		int rows = (Integer) bookDao.updateOne(book2);
		System.out.println("updatet "+rows+" rows");
		//三次测试失败了 说明 一件事 也就是 严格按照 字段来进行插入删除 
		//也就是 你数据库原本有数据 但这种数据本身没问题 但是一旦插入某个相同对象 
		//主键相同 但是其他被更新 即使为空  也会被覆盖 请小心
		//建议 更新手段 采用 删除旧有数据 插入新数据 这种方式
	}
	
	public static void test_delete()
	{
		Book book2 = new Book();
		book2.setId(1007);
		
		int rows = (Integer) bookDao.deletOne(book2);
		System.out.println("delete from book "+rows+"rows");
	}
	
	public static void test_select()
	{
		Book book = new Book();
		book.setId(1001);
		Book book1 = (Book) bookDao.selectOne(book);
		
		System.out.println(book1);
	}
	public static void main(String[] args) 
	{
		init();
		
		//测试插入
		//test_insert();
		
		//测试更新
		test_update();
				
		//测试删除
		//test_delete();
		
		//测试查询
		//test_select();		
		
		
	}
}
