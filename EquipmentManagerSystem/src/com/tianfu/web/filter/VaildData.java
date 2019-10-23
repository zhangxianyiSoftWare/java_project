package com.tianfu.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tianfu.domain.AJaxResponseMsg;
import com.tianfu.domain.UserForm;
import com.tianfu.domain.AJaxResponseMsg.ResponseCode;
import com.tianfu.utils.MergeUtils;
import com.tianfu.utils.SendDataUtils;

import net.sf.json.JSONObject;

/**
 * Servlet Filter implementation class VaildData
 */
/*@WebFilter("/VaildData")  注解方式 实在太难懂 不想用*/
public class VaildData implements Filter 
{
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		AJaxResponseMsg<Map<String, String>> msg = new AJaxResponseMsg<Map<String, String>>();
		Map<String, String> mapMsg = new HashMap<String, String>();
		//msg.setMsg("failed");
		if(! validCaptcha(request,response))
		{
			msg.setCode(ResponseCode.EXCEPTION);
			msg.setMsg("input data error");
			mapMsg.put("errorCaptcha", "Captcha is error");
			msg.setData(mapMsg);
			((HttpServletResponse)response).setStatus(500);
			SendDataUtils.flushAJAXData(msg, response);
			return;
		}
		//验证操作
		UserForm uf = new UserForm();
		//封装 userform 的数据
		MergeUtils.mergeAttribute(uf, (HttpServletRequest)request);
		if(! uf.vaildData())
		{
			mapMsg = uf.getMessage();
			msg.setCode(ResponseCode.EXCEPTION);
			msg.setMsg("input data error");
			msg.setData(mapMsg);
			//设置返回状态码
			((HttpServletResponse)response).setStatus(500);
			SendDataUtils.flushAJAXData(msg, response);
			return;
		}
		// pass the request along the filter chain
		HttpSession session = ((HttpServletRequest)request).getSession();
		session.setAttribute("userForm", uf);
		chain.doFilter(request, response);
	}

	
	private boolean validCaptcha(ServletRequest request, ServletResponse response)
	{
		//获取session 对象并取出购物车
		HttpSession session = ((HttpServletRequest) request).getSession();
		String captchaCode = (String) session.getAttribute("captcha");
		//如果验证码 和 输入的验证码相同 即可通过验证
		if(captchaCode.equalsIgnoreCase( request.getParameter("imageCode") )   )
		{
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	/**
     * Default constructor. 
     */
    public VaildData() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
