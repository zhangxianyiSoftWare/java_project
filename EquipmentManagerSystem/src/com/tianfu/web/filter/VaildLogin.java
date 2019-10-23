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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tianfu.domain.AJaxResponseMsg;
import com.tianfu.domain.AJaxResponseMsg.ResponseCode;
import com.tianfu.utils.SendDataUtils;

import net.sf.json.JSONObject;

/**
 * Servlet Filter implementation class VaildLogin
 */
public class VaildLogin implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		AJaxResponseMsg<Map<String, String>> msg = new AJaxResponseMsg<Map<String, String>>();
		Map<String, String> mapMsg = new HashMap<String, String>();
		if(! validCaptcha(request,response))
		{
			mapMsg.put("errorCaptcha", "Captcha is error");
		}
		if(! validPost_box(request,response))
		{
			//一旦异常 即可加入此信息
			msg.setMsg("input data error");
			mapMsg.put("errorPost_box", "the post box format is error");
		}
		//if the error msg is not empty 
		if(! mapMsg.isEmpty())
		{
			msg.setCode(ResponseCode.EXCEPTION);
			msg.setData(mapMsg);
			((HttpServletResponse)response).setStatus(500);
			SendDataUtils.flushAJAXData(msg, response);
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
	private boolean validCaptcha(ServletRequest request, ServletResponse response)
	{
		//获取session 对象并取出购物车
		HttpSession session = ((HttpServletRequest) request).getSession();
		String captchaCode = (String) session.getAttribute("captcha");
		//如果验证码 和 输入的验证码相同 即可通过验证
		//System.out.println("session 中的验证码；= "+captchaCode);
		if(captchaCode.equalsIgnoreCase( request.getParameter("imageCode") )   )
		{
			return true;
		}
		return false;
	}
	
	private boolean validPost_box(ServletRequest request, ServletResponse response)
	{
		String post_box = request.getParameter("post_box");
		//System.out.println("post_box = "+post_box);
		if(post_box.matches("[1-9][0-9]{5,10}@qq\\.[a-zA-Z]+"))
		{
			return true;
		}
		return false;
		//fileter 不知道为什么导致的 ajax 回调函数不起作用 只能无条件通过
	}
	
	/*************************************************************/
	/**
     * Default constructor. 
     */
    public VaildLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
