package com.tianfu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.tianfu.dao.Dao;
import com.tianfu.domain.Equipment;
import com.tianfu.domain.User;
import com.tianfu.utils.DBUtils;
import com.tianfu.utils.MergeUtils;

public class EquipmentDao implements Dao
{
	@Override
	public Object add(Object obj) {
		// TODO Auto-generated method stub
		Equipment equip = (Equipment)obj;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Connection conn = null;
		PreparedStatement pstate = null;
		String sql = "insert into equipment_t values(?,?,?,?,?,?,?)";
		
		Integer num = 0;
		try
		{
			conn = DBUtils.getConnection();
			pstate = conn.prepareStatement(sql);
			pstate.setString(1, equip.getName());
			pstate.setString(2, sdf.format(equip.getLogin_time()));
			pstate.setString(3, sdf.format(equip.getLogout_time()));
			pstate.setString(4, equip.getState().getDesc());
			pstate.setString(5, equip.getDone_thing());
			pstate.setString(6, equip.getImage_path());
			pstate.setString(7, equip.getCategory());
			num = pstate.executeUpdate();
			System.out.println("dao : add equipment: "+equip);
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
	public Object delete(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object find(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object change(Object obj, Object type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObj(Object obj) {
		// TODO Auto-generated method stub
		Equipment equip = (Equipment)obj;
		
		Equipment ret_equip = new Equipment();
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet result = null;
		String sql = "select * from equipment_t where name = ?";
		try 
		{
			conn = DBUtils.getConnection();
			pstat = conn.prepareStatement(sql);
			//赋值 给 sql 语句 进行
			pstat.setString(1, equip.getName());
			//执行操作
			result = pstat.executeQuery();
			while(result.next())
			{
				MergeUtils.mergeAttribute(ret_equip, result);
			}
			
			//System.out.println("after merege user = "+ret_user);
		} 
		catch (SQLException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DBUtils.closeAll(conn, pstat, result);
		}
		return ret_equip;
	}

	public List<Equipment> findAllEquips() throws SQLException 
	{
		// TODO Auto-generated method stub
		
		List<Equipment> equips = new ArrayList<Equipment>();
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet result = null;
		String sql = "select * from equipment_t";
		try 
		{
			conn = DBUtils.getConnection();
			pstat = conn.prepareStatement(sql);
			//执行操作
			result = pstat.executeQuery();
			MergeUtils.mergeAttribute(equips, result);
			//System.out.println("after merege user = "+ret_user);
		} 
		catch (SQLException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DBUtils.closeAll(conn, pstat, result);
		}
		return equips;
	}
	
	@Override
	public List<Object> findAll(int type) 
	{
		return null;
		
	}
	
}
