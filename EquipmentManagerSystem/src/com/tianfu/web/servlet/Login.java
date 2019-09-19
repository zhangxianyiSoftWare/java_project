package com.tianfu.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianfu.domain.User;
import com.tianfu.exception.UserExistException;
import com.tianfu.service.impl.UserManager;
import com.tianfu.utils.MergeUtils;

public class Login extends HttpServlet 
{
	/**
		 * Constructor of the object.
		 */
	public Login() 
	{
		super();
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//获取表单数据
		User user = new User();
		MergeUtils.mergeAttribute(user,request);
		 
		//调用业务逻辑
		UserManager service = new UserManager();		
		//分发转向
		try 
		{
			if(service.login(user))
			{
				request.getSession().setAttribute("login_user", user);
		    	request.getRequestDispatcher("/afterLogin/index.jsp").forward(request, response);
		    	return;
			}
			else 
			{
				request.setAttribute("errorPass", "密码不正确");
		    	request.getRequestDispatcher("/login.jsp").forward(request, response);
		    	return;
			}
		} 
		catch (UserExistException e) 
		{
			// TODO Auto-generated catch block
			request.setAttribute("errorMessage", "用户不存在 情先注册在登陆");
	    	request.getRequestDispatcher("/login.jsp").forward(request, response);
			e.printStackTrace();
			return;
		}
		
		
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
