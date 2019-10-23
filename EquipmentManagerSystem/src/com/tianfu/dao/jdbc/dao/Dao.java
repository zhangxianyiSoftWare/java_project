package com.tianfu.dao.jdbc.dao;

import java.util.List;

public interface Dao 
{
	public Object find(Object obj);
	public Object add(Object obj);
	public Object delete(Object obj);
	
	List<Object> findAll();
}
