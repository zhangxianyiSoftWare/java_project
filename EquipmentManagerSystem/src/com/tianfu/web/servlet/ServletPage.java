package com.tianfu.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianfu.domain.AJaxResponseMsg;
import com.tianfu.domain.Equipment;
import com.tianfu.domain.PageBean;
import com.tianfu.domain.PageInfo;
import com.tianfu.domain.AJaxResponseMsg.ResponseCode;
import com.tianfu.service.Service;
import com.tianfu.service.impl.EquipmentManager;
import com.tianfu.threadHandle.ResponseHighAjax;
import com.tianfu.utils.MergeUtils;
import com.tianfu.utils.SendDataUtils;

/**
 * Servlet implementation class ServletPage
 */
@WebServlet("/servlet/page")
public class ServletPage extends BaseServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPage() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void findEquipmentBymanyCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	//只要有请求过来 就启动线程去执行
    	ResponseHighAjax repsonHighAjaxThread  = new ResponseHighAjax(request,response);
    	repsonHighAjaxThread.run();
    	
    	return;
    }

	public void findObjByPageInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//封转对象 
		PageInfo page_info = new PageInfo();
		MergeUtils.mergeAttribute(page_info, request);
		//调用 业务逻辑
		Service service = new EquipmentManager();
		PageBean<Equipment> pageBean = service.findByPageInfo(page_info);
		
		//跳转页面
		AJaxResponseMsg<PageBean<Equipment>> msg = new AJaxResponseMsg<PageBean<Equipment>>();
		//刷新数据到前端去
		
		flushResponse2Client(pageBean,msg,response);
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
