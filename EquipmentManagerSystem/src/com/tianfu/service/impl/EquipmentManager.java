package com.tianfu.service.impl;


import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.tianfu.dao.mybatis.dao.EquipmentMapper;
import com.tianfu.domain.Equipment;
import com.tianfu.service.Service;
import com.tianfu.utils.SingletonSessionFacUtils;

public class EquipmentManager implements Service 
{
	public EquipmentManager()
	{
		
	}
	
	@Override
	public Object login(Object obj) 
	{
		
		return null;
	}

	@Override
	public Object regiest(Object obj) 
	{
		SqlSession session = SingletonSessionFacUtils.getInstance().openSession();
    	EquipmentMapper dao = session.getMapper(EquipmentMapper.class);
		Equipment equip = (Equipment) dao.find(obj);
		if(null == equip)
		{
			//如果没有
			if(dao.add(obj) > 0)
			{
				session.commit();
				session.close();
				return "succ";
			}
		}
		
		if(! equip.isEmpty())
		{
			session.close();
			return "existed";
		}
		
		session.close();
		return "failed";
	}

	public List<Object> findAll() {
		// TODO Auto-generated method stub
		SqlSession session = SingletonSessionFacUtils.getInstance().openSession();
    	EquipmentMapper dao = session.getMapper(EquipmentMapper.class);
		List<Object> list =  dao.findAll();
		session.close();
		return list;
	}
	
	public Integer update(Object obj)
	{
		int num = 0;
		SqlSession session = SingletonSessionFacUtils.getInstance().openSession();
    	EquipmentMapper dao = session.getMapper(EquipmentMapper.class);
    	//核心代码
		num =  dao.update(obj);
		
		session.commit();
		session.close();
		return num;
	}
	
	public Integer delete(Object obj)
	{
		int num = 0;
		SqlSession session = SingletonSessionFacUtils.getInstance().openSession();
    	EquipmentMapper dao = session.getMapper(EquipmentMapper.class);
    	//核心代码
		num =  dao.delete(obj);

		session.commit();
		session.close();
		return num;
	}

	
}
