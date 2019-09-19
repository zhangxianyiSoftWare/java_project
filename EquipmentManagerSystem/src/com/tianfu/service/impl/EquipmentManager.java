package com.tianfu.service.impl;
import java.sql.SQLException;
import java.util.List;

import com.tianfu.dao.Dao;
import com.tianfu.dao.impl.EquipmentDao;
import com.tianfu.domain.Equipment;
import com.tianfu.service.Service;

public class EquipmentManager implements Service 
{
	private Dao dao = new EquipmentDao();
	
	
	@Override
	public Object login(Object obj) 
	{
		// TODO Auto-generated method stub
		Equipment equip = (Equipment) dao.getObj(obj);
		if(! equip.isEmpty())
		{
			return "existed";
		}
		//如果没有
		if((Integer)dao.add(obj) > 0)
		{
			return "succ";
		}
		
		return "failed";
	}

	@Override
	public Object regiest(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Equipment> findAll() 
	{
		// TODO Auto-generated method stub
		EquipmentDao dao = new EquipmentDao();
		List<Equipment> equipments = null;
		try 
		{
			equipments = dao.findAllEquips();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return equipments;
	}
}
