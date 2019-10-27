package com.tianfu.service.impl;


import java.io.File;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.tianfu.dao.mybatis.dao.EquipmentMapper;
import com.tianfu.domain.AsyncRefreshRequest;
import com.tianfu.domain.Equipment;
import com.tianfu.domain.PageBean;
import com.tianfu.domain.PageInfo;
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
    	//删除文件
    	Equipment temp = (Equipment) dao.find(obj);
    	/*
    	 * 现一个好办法 用于删除 那些无用的空目录 虽然以后也有可能需要使用 
    	Integer index = temp.getImage_path().indexOf("/WEB-INF/upload/");
    	String file_direct = temp.getImage_path().substring(index);
    	System.out.println("file_delete_direct: "+file_direct);
    	*/
    	File d_file = new File(temp.getImage_path());
    	d_file.delete();
    	//核心代码
		num =  dao.delete(obj);
		session.commit();
		session.close();
		return num;
	}

	@Override
	public PageBean<Equipment> findAll(PageInfo info) 
	{
		return findByPageInfo(info);
	}
	
	@Override
	public PageBean<Equipment> findByPageInfo(PageInfo page_info)
	{
		//由于代码完全和 findALL  一样 仍然都是 根据页面信息查数据
		// TODO Auto-generated method stub
		SqlSession session = SingletonSessionFacUtils.getInstance().openSession();
    	EquipmentMapper dao = session.getMapper(EquipmentMapper.class);
    	Integer data_num = dao.getCount();
    	List<Equipment> list =  dao.findAll(page_info);
		session.close();

		//封转pageBean 属性
		PageBean<Equipment> pageBean = new PageBean<Equipment>();
		pageBean.setData(list);
		pageBean.setData_count(data_num);
		pageBean.setPage_curr(page_info.getPage_curr());
		//数据总数 / 页面长度 = 总页数 （向上取整 会 +1 （如果有余数））
		pageBean.setPage_num((int)Math.ceil((data_num * 1.0)/page_info.getPage_size()));
		pageBean.setPage_size(page_info.getPage_size());
		
		//System.out.println(pageBean);
		return pageBean;
	}

	@Override
	public PageBean<Equipment> findByPageInfo(AsyncRefreshRequest asyncRequest) {
		// TODO Auto-generated method stub
		//System.out.println("asyncRequest: "+asyncRequest);
		SqlSession session = SingletonSessionFacUtils.getInstance().openSession();
    	EquipmentMapper dao = session.getMapper(EquipmentMapper.class);
    	Integer data_num = dao.getCountByCommand(asyncRequest);
    	List<Equipment> list =  dao.findAllByCommand(asyncRequest);
		session.close();
		
		//封转pageBean 属性
		PageBean<Equipment> pageBean = new PageBean<Equipment>();
		pageBean.setData(list);
		pageBean.setData_count(data_num);
		pageBean.setPage_curr(asyncRequest.getPage_info().getPage_curr());
		//数据总数 / 页面长度 = 总页数 （向上取整 会 +1 （如果有余数））
		int num = (int)Math.ceil( (data_num * 1.0)/asyncRequest.getPage_info().getPage_size());
		if(num == 0)
		{
			pageBean.setPage_num(1);
		}
		pageBean.setPage_num(num);
		pageBean.setPage_size(asyncRequest.getPage_info().getPage_size());
		//System.out.println("asyncResponse: "+pageBean);
		return pageBean;
	}
}
