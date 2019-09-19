package com.tianfu.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
public class DBUtils 
{
	//create a data source
	private static DataSource data_source = new ComboPooledDataSource();
	//get a datasource
	public static DataSource getDataSource()
	{
		return data_source;
	}
	//get a connection from data source (coonection pool)
	public static Connection getConnection()
	{
		try 
		{
			return data_source.getConnection();
		} 
		catch (SQLException e) 
		{
        	e.printStackTrace();
        	throw new RuntimeException("服务繁忙");
		}
	}
	
	
	
	//关闭资源
    public static void closeAll(Connection conn,Statement stmt,ResultSet rs)
    {
		if(rs!=null)
		{
			try 
			{
				rs.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			rs = null;
		}
		if(stmt!=null)
		{
			try 
			{
				stmt.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			stmt = null;
		}
		
		if(conn!=null)
		{
			try 
			{
				//将连接交还给连接池 并非关闭连接通道
				conn.close();//关闭
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			conn = null;
		}
	}
}
