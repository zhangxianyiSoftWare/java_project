package com.tianfu.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.management.relation.Relation;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

	protected ServletConfig config;
	final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		rwl.writeLock().lock();
		this.config = config;
		rwl.writeLock().unlock();
		//System.out.println("init the config"+this.config);
	}
	/**
	 * Constructor of the object.
	 */
	public BaseServlet() 
	{
		super();
	}
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		//set the encoding 
		//get the method name
		String method_name = request.getParameter("method");
		try 
		{
			//get the name of method
			Method method = this.getClass().getDeclaredMethod(method_name, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request,response);
		} 
		catch (NoSuchMethodException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	catch (SecurityException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
	}

	
}
