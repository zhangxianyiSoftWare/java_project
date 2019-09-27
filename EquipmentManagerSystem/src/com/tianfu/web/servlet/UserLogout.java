package com.tianfu.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianfu.domain.User;
import com.tianfu.service.impl.UserManager;
import com.tianfu.utils.MergeUtils;

/**
 * Servlet implementation class UserLogout
 */
@WebServlet("/servlet/logout")
public class UserLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//获取表单数据
		User user = (User) request.getSession().getAttribute("login_user");
		//调用业务逻辑
		UserManager service = new UserManager();
		//分发转向
		try 
		{
			if(service.logout(user))
			{
				request.getSession().removeAttribute("login_user");
		    	request.getRequestDispatcher("/index.jsp").forward(request, response);
		    	return;
			}
			else 
			{
				request.setAttribute("errorLogout", "注销失败 请询问后台管理员");
		    	request.getRequestDispatcher("/afterLogin/index.jsp").forward(request, response);
		    	return;
			}
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			request.setAttribute("errorMessage", "用户不存在 情先注册在登陆");
	    	request.getRequestDispatcher("/login.jsp").forward(request, response);
			e.printStackTrace();
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
