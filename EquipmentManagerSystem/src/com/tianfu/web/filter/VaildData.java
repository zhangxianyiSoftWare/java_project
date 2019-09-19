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

import com.tianfu.domain.UserForm;
import com.tianfu.utils.MergeUtils;

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
		//验证码错误
		if(! validCaptcha(request,response))
		{
			request.setAttribute("errorCaptcha","验证码错误");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		//验证操作
		 UserForm uf = new UserForm();
		 //封装 userform 的数据
		 MergeUtils.mergeAttribute(uf, (HttpServletRequest)request);
		 
		 if(! uf.vaildData())
		 {
			 request.setAttribute("errorMessage", uf.getMessage());
			 request.getRequestDispatcher("/register.jsp").forward(request, response);
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
