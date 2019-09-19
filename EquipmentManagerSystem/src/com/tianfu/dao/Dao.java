package com.tianfu.dao;

import java.util.List;

public interface Dao 
{
	public Object add(Object obj);
	public Object delete(Object obj);
	public Object find(Object obj);
	public Object change(Object obj,Object type);
	//通过传入的对象 主键值 获取 数据库中的对象
	public Object getObj(Object obj);
	public List<Object> findAll(int type) ;
}
