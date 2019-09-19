<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>

<title>设备管理注册页面</title>
<%--导入css  使用绝对路径 保证servlet 转发回来的请求 资源路径正确--%>
<link rel="stylesheet" href="/EquipmentManagerSystem/css/main.css" type="text/css" />
<script type="text/javascript">
	function changeImage() 
	{
		document.getElementById("img").src = "${pageContext.request.contextPath}/servlet/otherfunc?method=captchaUser&time="
				+ new Date().getTime();
	}
</script>
</head>


<body class="main">
	<%@include file="jspComponent/head.jsp"%>
	<%--导入头 --%>
	<div id="divcontent">
		<form action="${pageContext.request.contextPath}/servlet/register"
			method="post">
			<table width="850px" border="0" cellspacing="0">
				<tr>
					<td style="padding:30px">
						<h1>新会员注册</h1>
						
						<table width="70%" border="0" cellspacing="2" class="upline">
							<tr>
								<td style="text-align:right; width:20%">会员邮箱：</td>
								<td style="width:40%">
								<input type="text" class="textinput" name="post_box" placeholder="请输入有效的邮箱地址"/></td>
								<td><font color="red">${errorMessage.post_box}</font></td>
							</tr>
							<tr>
								<td style="text-align:right">会员名：</td>
								<td>
									<input type="text" class="textinput" name="user_name" placeholder="用户名设置至少8位"/>
								</td>
								<td><font color="red">${errorMessage.user_name}</font></td>
							</tr>
							<tr>
								<td style="text-align:right">密码：</td>
								<td><input type="password" class="textinput" name="password" placeholder="至少8位的字母数字组合"/></td>
								<td><font color="red">${errorMessage.password}</font></td>
							</tr>
							<tr>
								<td style="text-align:right">重复密码：</td>
								<td><input type="password" class="textinput"
									name="repassword"  onblur="checkInfoPwd()"/></td>
								<td><span id="repwdmsg1"></span> </td>
							</tr>
							<tr>
								<td style="text-align:right">性别：</td>
								<td colspan="2">&nbsp;&nbsp;<input type="radio"
									name="sex" value="man" checked="checked" /> 男
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="sex" value="woman" /> 女</td>
							</tr>
							<tr>
								<td style="text-align:right">联系电话：</td>
								<td colspan="2">
								<input type="text" class="textinput" style="width:350px" name="telephone" placeholder="联系电话必须为11位的数字组合"/></td>
							</tr>
							<tr>
								<td style="text-align:right">个人介绍：</td>
								<td colspan="2"><textarea class="textarea" name="introduce_self"></textarea>
								</td>
							</tr>

						</table>



						<h1>注册校验</h1>
						<table width="80%" border="0" cellspacing="2" class="upline">
							<tr>
								<td style="text-align:right; width:20%">输入校验码：</td>
								<td style="width:50%"><input type="text" class="textinput" name="imageCode"/>
								</td>
								<td>&nbsp;<span color="red">${errorCaptcha}</span></td>
							</tr>
							<tr>
								<td style="text-align:right;width:20%;">&nbsp;</td>
								<td colspan="2" style="width:50%">
								<img src="${pageContext.request.contextPath}/servlet/otherfunc?method=captchaUser" width="180"
									 height="30" class="textinput" style="height:30px;" id="img" />&nbsp;&nbsp;
									<a href="javascript:void(0);" onclick="changeImage()">看不清换一张</a>
								</td>
							</tr>
						</table>



						<table width="70%" border="0" cellspacing="0">
							<tr>
								<td style="padding-top:20px; text-align:center">
								<input type="image" src="images/signup.gif" name="submit" border="0">
								</td>
							</tr>
						</table></td>
				</tr>
			</table>
		</form>
	</div>



	<div id="divfoot">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td rowspan="2" style="width:10%"><img
					src="images/bottomlogo.gif" width="195" height="50"
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
function checkInfoPwd()
{
	
	var pwd = document.getElementsByName("password")[0];
	var repwd = document.getElementsByName("repassword")[0];
	
	var msg = document.getElementById("repwdmsg1");
	if(pwd.value == repwd.value)
	{
		msg.innerHTML = "<font color='green'>合法的密码输入</font>";
		return true;
	}
	else
	{
		msg.innerHTML = "<font color='red'>两次输入的密码必须一致</font>";
		return false;
	}
}


</script>

</html>
