package com.tianfu.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.tianfu.domain.AJaxResponseMsg;
import com.tianfu.domain.AJaxResponseMsg.ResponseCode;

import net.sf.json.JSONObject;

public class SendDataUtils 
{
	public static void flushAJAXData(AJaxResponseMsg<Map<String, String>> msg,ServletResponse response)throws IOException, ServletException
	{
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json;charset=UTF-8");
		JSONObject responMsg = JSONObject.fromObject(msg);
		try 
	    {
			//将json 包 推送到前台去
	    	out.append(responMsg.toString());
	    	//System.out.println("response register json = "+responMsg.toString());
	    	//这里直接结束 验证失败 直接推送
	    	return;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			out.flush();
			if(out != null)
			{
				out.close();
			}
		}
	}
}
