package com.tianfu.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class VaildLogin
 */
public class VaildLogin implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//验证码错误
		if(! validCaptcha(request,response))
		{
			request.setAttribute("errorCaptcha","验证码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		String post_box = request.getParameter("post_box");
		//System.out.println("post_box = "+post_box);
		if(!post_box.matches("[1-9][0-9]{5,10}@qq\\.[a-zA-Z]+"))
		{
			request.setAttribute("errorPost_box","邮箱格式错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
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
		
		//System.out.println("input string imagecode = "+request.getParameter("imageCode"));
		//System.out.println("session 中的验证码；= "+captchaCode);
		if(captchaCode.equalsIgnoreCase( request.getParameter("imageCode") )   )
		{
			return true;
		}
		return false;
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
