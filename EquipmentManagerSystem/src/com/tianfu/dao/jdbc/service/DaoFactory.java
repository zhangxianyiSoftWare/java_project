package com.tianfu.dao.jdbc.service;

import com.tianfu.dao.jdbc.dao.Dao;
import com.tianfu.dao.jdbc.dao.impl.EquipmentDao;
import com.tianfu.dao.jdbc.dao.impl.UserDao;

public class DaoFactory 
{
	public static Dao getDao(String type)
	{
		Dao dao = null;
		switch (type) 
		{
			default:
				break;
			case "user":
				dao = new UserDao();
				break;
			case "equipment":
				dao = new EquipmentDao();
				break;
		
		}
		return dao;
	}
}
