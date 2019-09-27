<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
    <p>登陆后界面</p>
    	<!--${pageContext.request.contextPath}/afterLogin/addEquipment.jsp  -->
    	<a href="${pageContext.request.contextPath}/afterLogin/addEquipment.jsp">添加设备</a>
  		<br/>
  		<br/>
  		<a href="${pageContext.request.contextPath}/afterLogin/admin/admin.jsp">查看设备列表（需要最高权限）</a>
  		<span>${errorMessage }</span>
  		<br/>
  		<br/>
  		<a href="${pageContext.request.contextPath}/servlet/logout">用户注销</a>
  		<span>${errorLogout }</span>
  </body>
</html>
