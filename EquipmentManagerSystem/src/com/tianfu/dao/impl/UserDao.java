package com.tianfu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.tianfu.dao.Dao;
import com.tianfu.domain.User;
import com.tianfu.utils.DBUtils;
import com.tianfu.utils.MergeUtils;

public class UserDao implements Dao 
{

	@Override
	public Object add(Object obj) 
	{
		// TODO Auto-generated method stub
		User user = (User)obj;
		Connection conn = null;
		PreparedStatement pstate = null;
		String sql = "insert into user_t values(?,?,?,?,?,?,?)";
		
		Integer num = 0;
		try
		{
			conn = DBUtils.getConnection();
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, user.getPost_box());
			pstate.setString(2, user.getUser_name());
			pstate.setString(3, user.getPassword());
			pstate.setString(4, user.getSex());
			pstate.setString(5, user.getTelephone());
			pstate.setString(6, user.getIntroduce_self());
			//注册的时候 权限 一般限制为0 游客权限
			pstate.setString(7, "1");
			
			num = pstate.executeUpdate();
		}
		catch (SQLException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			DBUtils.closeAll(conn, pstate, null);
		}
		return num;
	}

	@Override
	public Object delete(Object obj) 
	{
		// TODO Auto-generated method stub
		User user = (User)obj;
		Connection conn = null;
		PreparedStatement pstate = null;
		String sql = "delete from user_t where post_box = ?";
		
		Integer num = 0;
		try
		{
			conn = DBUtils.getConnection();
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, user.getPost_box());
			
			num = pstate.executeUpdate();
			
		}
		catch (SQLException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			DBUtils.closeAll(conn, pstate, null);
		}
		
		return num;
	}

	@Override
	public Object find(Object obj) 
	{
		// TODO Auto-generated method stub
		User user = (User)obj;
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet result = null;
		String sql = "select post_box from user_t where post_box = ?";
		Integer num = 0;
		try 
		{
			conn = DBUtils.getConnection();
			pstat = conn.prepareStatement(sql);
			//赋值 给 sql 语句 进行
			pstat.setString(1, user.getPost_box());
			//执行操作
			result = pstat.executeQuery();
			while(result.next())
			{
				++num;
			}
		} 
		catch (SQLException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			DBUtils.closeAll(conn, pstat, result);
		}
		return num;
	}

	@Override
	public Object change(Object obj,Object type) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObj(Object obj) 
	{
		// TODO Auto-generated method stub
		User user = (User)obj;
		
		User ret_user = new User();
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet result = null;
		String sql = "select * from user_t where post_box = ?";
		try 
		{
			conn = DBUtils.getConnection();
			pstat = conn.prepareStatement(sql);
			//赋值 给 sql 语句 进行
			pstat.setString(1, user.getPost_box());
			//执行操作
			result = pstat.executeQuery();
			while(result.next())
			{
				MergeUtils.mergeAttribute(ret_user, result);
			}
			
			//System.out.println("after merege user = "+ret_user);
		} 
		catch (SQLException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			DBUtils.closeAll(conn, pstat, result);
		}
		return ret_user;
	}

	@Override
	public List<Object> findAll(int type) {
		// TODO Auto-generated method stub
		return null;
	}
}
