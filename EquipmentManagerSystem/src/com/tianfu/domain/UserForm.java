package com.tianfu.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.validation.constraints.Size;


public class UserForm 
{
	private String      post_box    	  = null;
	private String      user_name   	  = null;
	private String 	    password    	  = null;
	private String      sex        		  = null;
	private String      telephone         = null;
	private String      introduce_self    = null;
	
	private Map<String,String> message = new HashMap<String, String>();
	
	//判断是否为标准输入

	/*
	邮箱：  邮箱符合正则标准 
	用户名：必须输入，且3~8位的字母组成<br>
	密码：必须输入，3~8位的数组组成<br>
	确认密码：和密码保持一致<br>  利用js 完成
	性别：  必须式男 和女
	电话：必须输入，必须式数字
	自我介绍 可以乱写 也可以不填
	*
	*/
	
	/*这里过滤校验 和 js 校验 可以配合 
	 * 复杂的校验过程 必须写在过滤器中*/
	
	public Boolean vaildData()
	{
		//校验邮箱
		//注意 字符串形式的开始符号 和结束符号 
		//不要写在字符串形式的正则表达式中
		if("".equals(post_box))
    	{
    		message.put("post_box", "邮箱不能为空");
    	}
    	else if(!post_box.matches("[1-9][0-9]{5,10}@qq\\.[a-zA-Z]+"))//正则表达 限制字符个数
    	{
    		message.put("post_box", "邮箱格式不正确 仅仅支持qq邮箱");
    	}
		
		//is user name vaild
		//用户名的校验
    	if("".equals(user_name))
    	{
    		message.put("user_name", "用户名不能为空");
    	}
    	else if(!user_name.matches("\\w{8,16}"))//正则表达 限制字符个数
    	{
    		message.put("user_name", "用户名必须输入8到16位的字符");
    	}
    	
    	//密码正则 必须为 数组和字符的组合  
    	//(?!^\d+$)  排除全数字
    	//(?!^[A-z]+$)  排除全字母
    	//密码的校验
    	
    	if("".equals(password))
    	{
    		message.put("password", "密码不能为空");
    	}
    	else if(!password.matches("(?!^\\d+$)\\w{8,16}"))
    	{
    		message.put("password", "密码必须式包含数字字母的组合 最低8位");
    	}
    	
    	//校验电话号码 必须 式 11位的全数字
    	if("".equals(telephone))
    	{
    		message.put("telephone", "电话号码不能为空");
    	}
    	else if(!telephone.matches("[1-9][0-9]{10}"))
    	{
    		message.put("telephone", "电话号码必须为11位的数字串");
    	}
    	
    	//如果 错误消息中有数据 代不合法
		return message.isEmpty();
	}

	
	
	
	
	
	/*get and set method*/
	public String getPost_box() {
		return post_box;
	}

	public void setPost_box(String post_box) {
		this.post_box = post_box;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIntroduce_self() {
		return introduce_self;
	}

	public void setIntroduce_self(String introduce_self) {
		this.introduce_self = introduce_self;
	}

	public Map<String, String> getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "UserForm [post_box=" + post_box + ", user_name=" + user_name + ", password=" + password + ", sex=" + sex
				+ ", telephone=" + telephone + ", introduce_self=" + introduce_self + ", message=" + message + "]";
	}
}
