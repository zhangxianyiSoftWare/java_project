package com.tianfu.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tianfu.domain.User;
import com.tianfu.domain.UserForm;
import com.tianfu.exception.UserExistException;
import com.tianfu.service.impl.UserManager;
import com.tianfu.utils.MergeUtils;

public class Register extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Register() {
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
		//get the table data user fton
		HttpSession session = request.getSession();
		UserForm uForm = (UserForm) session.getAttribute("userForm");
		
		//调用业务逻辑  logic
		UserManager service = new UserManager();
		User user = new User(); 
		try 
		{
	    	MergeUtils.mergeAttribute(user, uForm);
			service.findUser(user);
		} 
		catch (UserExistException e) 
		{
			// TODO: handle exception
			request.setAttribute("errorMessage", e.getMessage());
	    	request.getRequestDispatcher("/jspComponent/registerFailed.jsp").forward(request, response);
	    	return;
		}
		
		//分发转向
		if(service.regiest(uForm))
		{
			response.sendRedirect(request.getContextPath()+"/jspComponent/registerSuccess.jsp");
			return ;
		}
		else 
		{
			request.getRequestDispatcher("/jspComponent/registerFailed.jsp").forward(request, response);
			return ;
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
