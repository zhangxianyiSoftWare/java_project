package com.tianfu.service.impl;

import org.apache.ibatis.session.SqlSession;

import com.tianfu.dao.UserMapper;
import com.tianfu.domain.User;
import com.tianfu.domain.UserForm;
import com.tianfu.exception.UserExistException;
import com.tianfu.utils.MergeUtils;
import com.tianfu.utils.SingletonSessionFacUtils;

public class UserManager 
{
	public UserManager()
	{
		
	}
	/*
     * login 
     * param user
     * */
    public boolean login(User user_) throws UserExistException
    {
    	SqlSession session = SingletonSessionFacUtils.getInstance().openSession();
		UserMapper dao = session.getMapper(UserMapper.class);
		User tmp = new User();
		tmp.setPost_box(user_.getPost_box());
    	User data_user = (User) dao.find(tmp);
    	session.close();
    	
    	//System.out.println("数据库user = "+data_user+"\n传入的user: "+user_);
    	if(data_user == null)
    	{
    		throw new UserExistException("用户不存在");
    	}
    	//如果存在这个对象
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
    
    public boolean logout(User user_)
    {
    	SqlSession session = SingletonSessionFacUtils.getInstance().openSession();
		UserMapper dao = session.getMapper(UserMapper.class);
    	//登录之后 注销用户 可以直接进行 因为之前已经验证过身份了
		Integer num = dao.delete(user_);
		session.commit();
    	session.close();
    	if(num > 0)
    	{
    		return true;
    	}
		return false;
    }
    
    
    /*
     * regiest
     * param user
     * */
    public boolean regiest(UserForm user_)
    {
    	User user = new User(); 
    	MergeUtils.mergeAttribute(user, user_);
    	//默认权限
    	user.setAuthority(1);
    	
    	SqlSession session = SingletonSessionFacUtils.getInstance().openSession();
    	UserMapper dao = session.getMapper(UserMapper.class);
    	//不存在user  add return >0 mean succ  0means failed 
    	if(dao.add(user) > 0)
    	{
    		session.commit();
    		session.close();
    		return true;
    	}
    	session.close();
    	return false;
    }
    
    public Integer findUser(User user_) throws UserExistException
    {
    	SqlSession session = SingletonSessionFacUtils.getInstance().openSession();
    	UserMapper dao = session.getMapper(UserMapper.class);
    	int num = 1;
    	//默认存在
    	User user = (User)dao.find(user_);
    	session.close();
    	if(user==null || user.isEmpty())
    	{
    		//吐过不存在 则 置为0
    		num = 0;
    	}
    	return num;
    }
}
