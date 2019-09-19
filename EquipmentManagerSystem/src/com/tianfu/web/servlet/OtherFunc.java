package com.tianfu.web.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dsna.util.images.ValidateCode;

public class OtherFunc extends BaseServlet 
{
	/**
		 * Constructor of the object.
		 */
	public OtherFunc() {
		super();
	}

	public void captchaUser(HttpServletRequest request, HttpServletResponse response)
	{
		ValidateCode vc = new ValidateCode(110, 25, 4, 9);
		//将验证码写入session 中
		request.getSession().setAttribute("captcha", vc.getCode());
		try 
		{
			vc.write(response.getOutputStream());
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
