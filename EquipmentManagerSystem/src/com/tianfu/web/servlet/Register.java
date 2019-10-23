package com.tianfu.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tianfu.domain.AJaxResponseMsg;
import com.tianfu.domain.User;
import com.tianfu.domain.UserForm;
import com.tianfu.domain.AJaxResponseMsg.ResponseCode;
import com.tianfu.exception.UserExistException;
import com.tianfu.service.impl.UserManager;
import com.tianfu.utils.MergeUtils;
import com.tianfu.utils.SendDataUtils;

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
		AJaxResponseMsg<Map<String, String>> msg = new AJaxResponseMsg<Map<String, String>>();
		Map<String, String> mapMsg = new HashMap<String, String>();
		//get the table data user fton
		HttpSession session = request.getSession();
		UserForm uForm = (UserForm) session.getAttribute("userForm");
		//调用业务逻辑  logic
		UserManager service = new UserManager();
		User user = new User(); 
    	MergeUtils.mergeAttribute(user, uForm);
		Integer num;
		try 
		{
			num = service.findUser(user);
			if(num > 0)
			{
				// TODO: handle exception
				msg.setCode(ResponseCode.REGISTER_FAILED);
				msg.setMsg("valid exception");
				response.setStatus(500);
				mapMsg.put("errorPost_box", "user has exist");
				SendDataUtils.flushAJAXData(msg, response);
		    	return;
			}
		} catch (UserExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//分发转向
		if(service.regiest(uForm))
		{
			//注册成功重定向 清除所有的request  和response  数据
			msg.setCode(ResponseCode.REGISTER_SUCC);
			msg.setMsg("pass valid");
			mapMsg.put("path","/htmlComponent/registerSuccess.html");
			msg.setData(mapMsg);
			response.setStatus(200);
			SendDataUtils.flushAJAXData(msg, response);
			return ;
		}
		else 
		{
			//请求转发 转发 规定的request 数据
			msg.setCode(ResponseCode.REGISTER_FAILED);
			msg.setMsg("not pass valid");
			mapMsg.put("path","/htmlComponent/registerFailed.html");
			msg.setData(mapMsg);
			//保证 调用 正确的回调函数
			response.setStatus(200);
			SendDataUtils.flushAJAXData(msg, response);
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
