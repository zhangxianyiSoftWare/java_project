package com.tianfu.threadHandle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianfu.domain.AJaxResponseMsg;
import com.tianfu.domain.Equipment;
import com.tianfu.domain.PageBean;
import com.tianfu.domain.AJaxResponseMsg.ResponseCode;
import com.tianfu.domain.AsyncRefreshRequest;
import com.tianfu.service.Service;
import com.tianfu.service.impl.EquipmentManager;
import com.tianfu.utils.MergeUtils;
import com.tianfu.utils.SendDataUtils;

public class ResponseHighAjax implements Runnable
{
	private static Integer thread_count = 0;
	
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	
	public ResponseHighAjax(HttpServletRequest req,HttpServletResponse rep)
	{
		this.request = req;
		this.response = rep;
		//统级线程个数
		++thread_count;
	}
	
	@Override
	public void run() 
	{
		AsyncRefreshRequest asyncRequest = new AsyncRefreshRequest();
		//封转对象 
		MergeUtils.mergeAttribute(asyncRequest, request);
		//调用 业务逻辑
		Service service = new EquipmentManager();
		PageBean<Equipment> pageBean = service.findByPageInfo(asyncRequest);
		//跳转页面
		AJaxResponseMsg<PageBean<Equipment>> msg = new AJaxResponseMsg<PageBean<Equipment>>();
		//刷新数据到前端去
		
		try 
		{
			flushResponse2Client(pageBean,msg,response);
		} 
		catch (ServletException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//执行完毕线程数 -1
		thread_count--;
	}
	
	
	private void flushResponse2Client(PageBean<Equipment> pageBean,AJaxResponseMsg<PageBean<Equipment>> msg,HttpServletResponse response)throws ServletException, IOException 
	{
		if(! pageBean.isEmpty())
		{
			msg.setCode(ResponseCode.SELECT_EQUIP_SUCC);
			msg.setMsg("select succ");
			//讲路径封转
			msg.setData(pageBean);
			response.setStatus(200);
			SendDataUtils.flushAJAXSelectData(msg, response);
		}
		else 
		{
			msg.setCode(ResponseCode.SELECT_EQUIP_FAILED);
			msg.setMsg("select failed");
			//讲路径封转
			msg.setData(pageBean);
			response.setStatus(500);
			SendDataUtils.flushAJAXSelectData(msg, response);
		}
	}
}
