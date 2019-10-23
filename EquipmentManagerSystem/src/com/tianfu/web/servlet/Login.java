package com.tianfu.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianfu.domain.AJaxResponseMsg;
import com.tianfu.domain.User;
import com.tianfu.domain.AJaxResponseMsg.ResponseCode;
import com.tianfu.exception.UserExistException;
import com.tianfu.service.impl.UserManager;
import com.tianfu.utils.MergeUtils;
import com.tianfu.utils.SendDataUtils;

import net.sf.json.JSONObject;

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
		//获取request 中 所拥有的msg 对象
		AJaxResponseMsg<Map<String, String>> msg = new AJaxResponseMsg<Map<String, String>>();
		Map<String, String> mapMsg = new HashMap<String, String>();
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
				msg.setCode(ResponseCode.LOGIN_SUCC);
				msg.setMsg("pass valid");
				//讲路径封转
				mapMsg.put("path","/afterLogin/index.html");
				msg.setData(mapMsg);
				response.setStatus(200);
				SendDataUtils.flushAJAXData(msg, response);
				return;
			}
			else 
			{
				//密码不正确的情况下
				msg.setMsg("valid error");
				mapMsg.put("errorPass", "password is error");
				response.setStatus(500);
				SendDataUtils.flushAJAXData(msg, response);
				return;
			}
		} 
		catch (UserExistException e) 
		{
			// TODO Auto-generated catch block
			mapMsg.put("errorPost_box", "the user is not exist please register");
			msg.setData(mapMsg);
			response.setStatus(500);
			SendDataUtils.flushAJAXData(msg, response);
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
