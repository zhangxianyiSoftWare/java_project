package com.tianfu.service.impl;

import com.tianfu.dao.Dao;
import com.tianfu.dao.impl.UserDao;
import com.tianfu.domain.User;
import com.tianfu.domain.UserForm;
import com.tianfu.exception.UserExistException;
import com.tianfu.utils.MD5Utils;
import com.tianfu.utils.MergeUtils;

public class UserManager 
{
	Dao dao = new UserDao();
	/*
     * login 
     * param user
     * */
    public boolean login(User user_) throws UserExistException
    {
    	User data_user = (User) dao.getObj(user_);
    	
    	//System.out.println("数据库user = "+data_user+"\n传入的user: "+user_);
    	
    	//如果存在这个对象
    	if(! data_user.isEmpty())
    	{
    		//经过md5  加密之后的密码 值 如果相同 说明是同一个密码
    		if(user_.getPassword().equals(data_user.getPassword()))
        	{
    			MergeUtils.mergeAttribute(user_, data_user);
        		return true;
        	}
        	else 
        	{
    			return false;
    		}
    	}
    	throw new UserExistException("用户不存在");
    }
    
    
    /*
     * regiest
     * param user
     * */
    public boolean regiest(UserForm user_)
    {
    	User user = new User(); 
    	MergeUtils.mergeAttribute(user, user_);
    	//不存在user  add return >0 mean succ  0means failed 
    	if(((Integer)dao.add(user)) > 0)
    	{
    		return true;
    	}
    	return false;
    }
    
    public void findUser(User user) throws UserExistException
    {
    	if( (Integer)dao.find(user) > 0)
    	{
    		throw new UserExistException("用户已经存在");
    	}
    }
}
