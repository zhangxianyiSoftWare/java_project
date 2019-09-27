package com.tianfu.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tianfu.dao.EquipmentMapper;
import com.tianfu.dao.UserMapper;
import com.tianfu.entity.Equipment;
import com.tianfu.entity.User;
import com.tianfu.utils.SingletonSessionFacUtils;

public class FaceIntertest 
{
	public static void main(String[] args) 
	{
		SqlSession session = SingletonSessionFacUtils.getInstance().openSession();
		EquipmentMapper dao = session.getMapper(EquipmentMapper.class);
		
		Equipment tmp = new Equipment();
		tmp.setName("ax");
		tmp.setDone_thing("water");
		
		Integer num= dao.update(tmp);
		System.out.println("update rows"+num+"rows");
		session.commit();
		session.close();
	}
	
	
	
	
	
	
	
	
	
	public static void test_findAll()
	{
		SqlSession session = SingletonSessionFacUtils.getInstance().openSession();
		EquipmentMapper dao = session.getMapper(EquipmentMapper.class);
		
		Equipment tmp = new Equipment();
		tmp.setName("dx1906");

		
		List<Object> equips = dao.findAll();
		session.close();
	}
	
	public static void Useradd()
	{
		SqlSession session = SingletonSessionFacUtils.getInstance().openSession();
		
		UserMapper dao = session.getMapper(UserMapper.class);
		User user_ = new User();
		user_.setPost_box("1725460655@qq.com");
		user_.setAuthority(1);
		user_.setIntroduce_self("asb");
		user_.setPassword("a123123123");
		user_.setSex("man");
		user_.setTelephone("15623456845");
		user_.setUser_name("qweasdzxc");
		
		Integer num = dao.add(user_);
		System.out.println(num);
		//User user = (User)dao.find(user_);
		session.commit();
		session.close();
		//System.out.println("find User "+ user);	
	}
}
