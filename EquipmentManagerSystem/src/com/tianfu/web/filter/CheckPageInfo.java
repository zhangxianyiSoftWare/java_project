package com.tianfu.web.filter;

import java.io.IOException;
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

import com.tianfu.domain.AJaxResponseMsg;
import com.tianfu.domain.PageInfo;
import com.tianfu.domain.AJaxResponseMsg.ResponseCode;
import com.tianfu.utils.MergeUtils;
import com.tianfu.utils.SendDataUtils;

/**
 * Servlet Filter implementation class CheckPageInfo
 */
@WebFilter("/servlet/page")
public class CheckPageInfo implements Filter {
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//此过滤器的 数据合法功能 由前端进行完成 唯独检查 数据是否为空
		String page_curr = request.getParameter("page_curr");
		String page_num  = request.getParameter("page_num");
		String page_size = request.getParameter("page_size");
		
		if((page_curr =="" || page_curr ==null) ||
				(page_num =="" || page_num ==null) ||
				(page_size =="" || page_size ==null) )
		{
			AJaxResponseMsg<Map<String, String>> msg = new AJaxResponseMsg<Map<String, String>>();
			msg.setCode(ResponseCode.ERROR);
			msg.setMsg("data is null");
			SendDataUtils.flushAJAXData(msg, response);
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
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
    public CheckPageInfo() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
