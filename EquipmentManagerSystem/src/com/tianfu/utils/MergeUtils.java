package com.tianfu.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianfu.domain.EquipWorkState;
import com.tianfu.domain.Equipment;
import com.tianfu.domain.User;
import com.tianfu.domain.UserForm;

public class MergeUtils 
{
	/*
	 * param user request
	 * merge
	 * */
	public static void mergeAttribute(UserForm user,HttpServletRequest request)
	{
		user.setPost_box(request.getParameter("post_box"));
		user.setUser_name(request.getParameter("user_name"));
		user.setPassword(request.getParameter("password"));
		user.setSex(request.getParameter("sex"));
		user.setTelephone(request.getParameter("telephone"));
		user.setIntroduce_self(request.getParameter("introduce_self"));
	}
	
	public static void mergeAttribute(Equipment equip,HttpServletRequest request)
	{
		//因为上传文件格式的原因 导致request  不能获取到真真意义上的参数 故采跳转方式
		equip.setLogin_time(new Date());
		equip.setState(EquipWorkState.WORKING);
		//设置默认登出时间 不然解析 会报空指针异常
		long currentTime = System.currentTimeMillis() + 3 * 60 * 60 * 1000;
		Date date = new Date(currentTime);
		equip.setLogout_time(date);
	}
	
	public static void mergeAttribute(User user,HttpServletRequest request)
	{
		user.setPost_box(request.getParameter("post_box"));
		//将密码加密
		user.setPassword(MD5Utils.getMD5(request.getParameter("password") ) );
	}
	
	/*
	 * param user UserForm
	 * merge
	 * */
	public static void mergeAttribute(User user,UserForm userform)
	{
		user.setPost_box(userform.getPost_box());
		user.setUser_name(userform.getUser_name());
		user.setSex(userform.getSex());
		user.setTelephone(userform.getTelephone());
		user.setIntroduce_self(userform.getIntroduce_self());
		//将密码加密
		user.setPassword(MD5Utils.getMD5(userform.getPassword() ) );
	}
	
	public static void mergeAttribute(User user,User data_user)
	{
		user.setPost_box(data_user.getPost_box());
		user.setUser_name(data_user.getUser_name());
		user.setSex(data_user.getSex());
		user.setTelephone(data_user.getTelephone());
		user.setIntroduce_self(data_user.getIntroduce_self());
		user.setAuthority(data_user.getAuthority());
	}
	
	public static void mergeAttribute(User user,ResultSet result)throws SQLException
	{
		user.setPost_box(result.getString("post_box"));
		user.setUser_name(result.getString("user_name"));
		user.setPassword(result.getString("password"));
		user.setSex(result.getString("sex"));
		user.setTelephone(result.getString("telephone"));
		user.setAuthority(Integer.parseInt(result.getString("anthority")));
		user.setIntroduce_self(result.getString("introduce_self"));
	}
	
	public static void mergeAttribute(Equipment equip,ResultSet result)throws SQLException, ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		equip.setName(result.getString("name"));
		equip.setLogin_time(sdf.parse(result.getString("login_time")));
		equip.setLogout_time(sdf.parse(result.getString("logout_time")));
		equip.setDone_thing(result.getString("done_thing"));
		
		String data_state = result.getString("state");
		if("working".equalsIgnoreCase(data_state))
		{
			equip.setState(EquipWorkState.WORKING);
		}
		else if("ended".equalsIgnoreCase(data_state))
		{
			equip.setState(EquipWorkState.END);
		}
		else if("broken".equalsIgnoreCase(data_state))
		{
			equip.setState(EquipWorkState.BROKEN);
		}
		
		equip.setImage_path(result.getString("image_path"));
		equip.setCategory(result.getString("category"));
	}

	public static void mergeAttribute(List<Equipment> equips, ResultSet result) throws SQLException, ParseException {
		// TODO Auto-generated method stub
		while(result.next())
		{
			Equipment equip = new Equipment();
			mergeAttribute(equip, result);
			equips.add(equip);
		}
	}
}
