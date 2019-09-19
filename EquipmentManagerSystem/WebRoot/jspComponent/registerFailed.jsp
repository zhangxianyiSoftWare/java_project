<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>

<title>注册失败界面</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />

<script type="text/javascript" src="../js/my.js">
	
</script>
</head>

<body class="main">

	<jsp:include page="head.jsp"></jsp:include>
	<div id="divcontent">
		<table width="850px" border="0" cellspacing="0">
			<tr>
				<td style="padding:30px; text-align:center"><table width="60%"
						border="0" cellspacing="0" style="margin-top:70px">
						<tr>
							<td style="width:98"><img
								src="../images/error.jpg" width="128" height="128" />
							</td>
							<td style="padding-top:30px"><font
								style="font-weight:bold; color:#FF0000">注册失败,${errorMessage}</font><br />
								<br /> <a href="../index.jsp"><span id="seconds">5</span>秒后自动为您转跳回首页</a>
							</td>
						</tr>
					</table>
					<h1>&nbsp;</h1></td>
			</tr>
		</table>
	</div>



	<div id="divfoot">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td rowspan="2" style="width:10%"><img
					src="../images/bottomlogo.gif" width="195" height="50"
					style="margin-left:175px" /></td>
				<td style="padding-top:5px; padding-left:50px"><a href="#"><font
						color="#747556"><b>CONTACT US</b> </font> </a></td>
			</tr>
			<tr>
				<td style="padding-left:50px"><font color="#CCCCCC"><b>COPYRIGHT
							2019 &copy; eShop All Rights RESERVED.</b> </font></td>
			</tr>
		</table>
	</div>


</body>
<script type="text/javascript">
    var time = 5;
	var timer = setInterval
	(
	  function()
	  {
	    var current_secend = document.getElementById("seconds");
		if(time >=1)
		{
		   time--;
		   current_secend.innerHTML = time;
		   
		   if(time <= 3)
		   {
		     current_secend.style.color = "red";	 
		   }
		}
		else
		{
		   clearInterval(timer);
		   location.href = "../index.jsp" ;
		}
	  },
	  1000
	);
	
</script>
</html>
