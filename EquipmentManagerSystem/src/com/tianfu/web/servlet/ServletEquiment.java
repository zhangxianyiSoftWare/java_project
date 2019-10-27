package com.tianfu.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.tianfu.domain.AJaxResponseMsg;
import com.tianfu.domain.Equipment;
import com.tianfu.domain.PageBean;
import com.tianfu.domain.PageInfo;
import com.tianfu.domain.AJaxResponseMsg.ResponseCode;
import com.tianfu.service.Service;
import com.tianfu.service.impl.EquipmentManager;
import com.tianfu.utils.DirectoryUtils;
import com.tianfu.utils.MergeUtils;
import com.tianfu.utils.SendDataUtils;

public class ServletEquiment extends BaseServlet 
{
	public ServletEquiment()
	{
		super();
	}
	/*
	 * name findEquipmentBymanyCondition
	 * para request
	 * para response
	 * */
	public void findAllEquipments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//获取传入的页面信息
		PageInfo page_info = new PageInfo();
		MergeUtils.mergeAttribute(page_info, request);
		//System.out.println(page_info);
		//调用 业务逻辑
		Service service = new EquipmentManager();
		PageBean<Equipment> pageBean = service.findAll(page_info);
		//跳转页面
		AJaxResponseMsg<PageBean<Equipment>> msg = new AJaxResponseMsg<PageBean<Equipment>>();
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
	
	
	/*
	 * name addEquiment
	 * para request
	 * para response
	 * */
	public void addEquipment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//get the encoding
		AJaxResponseMsg<Map<String, String>> msg = new AJaxResponseMsg<Map<String, String>>();
		Map<String, String> mapMsg = new HashMap<String, String>();
		
		
		Equipment equip = new Equipment();
		MergeUtils.mergeAttribute(equip, request);
		//解析上传文件 
		parseUploadFile(equip,request,response);
		Service service = new EquipmentManager();
		String ret = (String)service.regiest(equip);
		if("succ".equals(ret) || "existed".equals(ret))
		{
			msg.setCode(ResponseCode.ADD_EQUIP_SUCC);
			msg.setMsg("pass valid");
			//讲路径封转
			mapMsg.put("path","/afterLogin/responseHTML/addSucc.html");
			msg.setData(mapMsg);
			response.setStatus(200);
			SendDataUtils.flushAJAXData(msg, response);
			return;
		}
		msg.setCode(ResponseCode.ADD_EQUIP_FAILED);
		msg.setMsg("not pass valid");
		//讲路径封转
		mapMsg.put("path","/afterLogin/responseHTML/addFailed.html");
		msg.setData(mapMsg);
		response.setStatus(200);
		SendDataUtils.flushAJAXData(msg, response);
		return;
	}
	public void updateEquipment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		//get the encoding
		Equipment equip = new Equipment();
		//解析上传文件 
		parseUploadFile(equip,request,response);
		equip.setName(request.getParameter("name"));
		Service service = new EquipmentManager();
		//System.out.println("uppdate equip "+equip);
		Integer ret = service.update(equip);
		//如果更新条数 > 0 更新完成
		AJaxResponseMsg<Map<String, String>> msg = new AJaxResponseMsg<Map<String, String>>();
		if(ret > 0 )
		{
			
			msg.setCode(ResponseCode.SUCCESS);
			msg.setMsg("update succ");
			//讲路径封转
			response.setStatus(200);
			SendDataUtils.flushAJAXData(msg, response);
			return;
		}
		msg.setCode(ResponseCode.ERROR);
		msg.setMsg("update error");
		//讲路径封转
		response.setStatus(500);
		SendDataUtils.flushAJAXData(msg, response);
		return;
    }
	
	public void deleteEquipment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//get the encoding
		Equipment equip = new Equipment();
		//设置固定的
		equip.setName(request.getParameter("name"));
		//System.out.println("nedd delete equipment"+equip);
		Service service = new EquipmentManager();
		Integer ret = service.delete(equip);
		AJaxResponseMsg<Map<String, String>> msg = new AJaxResponseMsg<Map<String, String>>();
		if(ret > 0 )
		{
			
			msg.setCode(ResponseCode.SUCCESS);
			msg.setMsg("delete succ");
			//讲路径封转
			response.setStatus(200);
			SendDataUtils.flushAJAXData(msg, response);
			return;
		}
		msg.setCode(ResponseCode.ERROR);
		msg.setMsg("delete error");
		//讲路径封转
		response.setStatus(500);
		SendDataUtils.flushAJAXData(msg, response);
		return;
	}
	
	
	//download file
	//para request
	//para response
	public void downloadFile(HttpServletRequest request, HttpServletResponse response)
	{
		response.setCharacterEncoding("text/html; charset=UTF-8"); 
		// TODO Auto-generated method stub
		//根据路径获取文件输入流
		String path =request.getParameter("path");
		FileInputStream fis = null;
		ServletOutputStream sobs= null;
		try 
		{
			fis = new FileInputStream(path);
			//获取文件名字  
			String file_name = path.substring(path.lastIndexOf("\\")+1);
			//设置文件名字的编码  用于确保安装
			//将不安全的名字字符 转换成可以识别的字符 避免出现无法识别的的响应
			file_name = URLEncoder.encode(file_name, "UTF-8");
			//通知客户端 下载文件而非展现
			response.setHeader("content-disposition", "attachment;filename="+file_name);
			response.setHeader("content-type", "image/jpeg");
			//创建字节输出流
			sobs = response.getOutputStream();
			//执行输出操作
			int len = 1;
			byte[] buffer = new byte[1024];
			while((len = fis.read(buffer)) != -1)
			{
				sobs.write(buffer,0,len);
			}
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				sobs.close();
				fis.close();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	//parse  upload file item
	private void parseUploadFile(Equipment equip,HttpServletRequest request, HttpServletResponse response)
	{
		// 要执行文件上传的操作
		// 判断表单是否支持文件上传。即：enctype="multipart/form-data"
		if(! ServletFileUpload.isMultipartContent(request)) 
		{
			throw new RuntimeException("your form is not multipart/form-data");
		}
		// 创建一个DiskFileItemfactory工厂类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File("D:\\temp"));// 指定临时文件的存储目录
		// 创建一个ServletFileUpload核心对象
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 解决上传表单项乱码问题
		sfu.setHeaderEncoding("UTF-8");
		// 限制上传文件的大小
		sfu.setFileSizeMax(1024*1024*2);//表示3M大小
		
		try 
		{
			//获取 request 中 所有的 表单项
			List<FileItem> items = (List<FileItem>) sfu.parseRequest(request);
			for(FileItem item: items)
			{
				//得到上传文件的表单项
				if(!item.isFormField())
				{
					parseUploadFile(item,equip);
				}
				else 
				{
					parseFormItem(item,equip);
				}
			}
		}
		catch (FileUploadBase.FileSizeLimitExceededException e) 
		{
			// throw new RuntimeException("文件过在，不能超过3M");
			System.out.println("文件过大，不能超过2M");
		} catch (FileUploadBase.SizeLimitExceededException e) {
			System.out.println("总文件大小不能超过6M");
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	private void parseFormItem(FileItem fileitem,Equipment equip)
	{
		try 
		{
			String fieldname = fileitem.getFieldName();// 字段名
			String fieldvalue = fileitem.getString("UTF-8");// 字段值
			fieldvalue = new String(fieldvalue.getBytes("iso-8859-1"),"utf-8");
			switch (fieldname) 
			{
			case "equip_name":
				equip.setName(fieldvalue);
				break;
			case "state":
				if("working".equalsIgnoreCase(fieldvalue))
				{
					equip.setState("WORKING");
				}
				else if("ended".equalsIgnoreCase(fieldvalue))
				{
					equip.setState("ENDED");
				}
				else if("broken".equalsIgnoreCase(fieldvalue))
				{
					equip.setState("BROKEN");
				}
				break;
			case "category":
				URLEncoder.encode(fieldvalue, "UTF-8");
				equip.setCategory(fieldvalue);
			default:
				break;
			}
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void parseUploadFile(FileItem fileitem,Equipment equip)
	{
		try 
		{
			//获取绝对的路径
			String directoryRealPath = this.getServletContext().getRealPath("/WEB-INF/upload");
			//既是文件 也是路径 是保存的路径
			File storeDirectory = new File(directoryRealPath);
			//如果该路径不存在 直接创建
			if(! storeDirectory.exists())
			{
				storeDirectory.mkdirs();
			}
			//得到 上传的文件名字
			String fileName = fileitem.getName();
			//处理文件名
			if(fileName != null)
			{
				// filename.substring(filename.lastIndexOf(File.separator)+1);
				fileName = FilenameUtils.getName(fileName);// 效果同上
			}
			else 
			{
				//没有上传文件直接返回
				return;
			}
			//同名文件的覆盖问题
			fileName = UUID.randomUUID() + "_" + fileName;
			//打散目录
			String childDirectory = DirectoryUtils.makeChildDirectory(storeDirectory, fileName);
			// 在storeDirectory下，创建完整目录下的文件
			File file = new File(storeDirectory, childDirectory+ File.separator + fileName); // 绝对目录/日期目录/文件名
			// 通过文件输出流将上传的文件保存到磁盘
			//通过文件输出流 将文件存储在服务器的磁盘上
			fileitem.write(new File(storeDirectory, childDirectory+ File.separator + fileName));
			//删除临时文件
			fileitem.delete();
			//空文件目录 需要删除？
			
			//设置equip 的 文件路径
			String file_path = file.getAbsolutePath();
			file_path = file_path.replaceAll("\\\\", "/");
			equip.setImage_path(file_path);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
