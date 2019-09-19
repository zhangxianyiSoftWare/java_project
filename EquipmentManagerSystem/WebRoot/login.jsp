<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>设备管理系统</title>

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

	<jsp:include page="jspComponent/head.jsp" />
	<div id="divcontent">
		<form action="${pageContext.request.contextPath}/servlet/login" method="post">
			<table width="900px" border="0" cellspacing="0">
				<tr>
					<td style="padding:30px"><div style="height:470px">
							<b>&nbsp;&nbsp;首页&nbsp;&raquo;&nbsp;个人用户登录</b>
							<div>
								<table width="100%" border="0" cellspacing="0">
									<tr>
										<td>
											<div id="logindiv">
												<table width="95%" border="0" cellspacing="0">
													<tr>
														<td style="text-align:center; padding-top:20px"><img
															src="images/logintitle.gif" width="150" height="30" />
														</td>
													</tr>
													<tr>
														<td style="text-align:center;padding-top:20px;"><font
															color="#ff0000">${requestScope["register_message"]}</font>
														</td>
													</tr>
													<tr>
														<td style="text-align:center">
															<table width="80%" border="0" cellspacing="0"
																style="margin-top:15px ;margin-left:auto; margin-right:auto">
																<tr>
																	<td
																		style="text-align:right; padding-top:5px; width:25%">用户名：</td>
																	<td style="text-align:left">
																	<input name="post_box" type="text" class="textinput" placeholder="请输入注册邮箱" />
																	</td>
																	<td>
																		<span >${errorPost_box}${errorMessage}</span>
																	</td>
																</tr>
																<tr>
																	<td style="text-align:right; padding-top:5px">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
																	<td style="text-align:left">
																	<input name="password" type="password" class="textinput" />
																	</td>
																	<td>
																		<span >${errorPass}</span>
																	</td>
																</tr>
																<tr>
																	<td colspan="2" style="text-align:center">
																	<input  type="checkbox" name="checkbox" value="checkbox" />
																			记住用户名&nbsp;&nbsp; <input type="checkbox"
																			name="checkbox" value="checkbox" /> 自动登录</td>
																</tr>
																<!-- 注册码 -->	
																<tr>
																	<td style="text-align:right; width:25%">输入校验码：</td>
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
																<!-- 注册码 ********************************-->		
																<tr>
																	<td colspan="2"
																		style="padding-top:10px; text-align:center">
																		<input	name="image" type="image" onclick="return formcheck()"
																				src="images/loginbutton.gif" width="83" height="22" />
																	</td>
																</tr>

																<tr>
																	<td colspan="2" style="padding-top:10px"><img
																		src="images/loginline.gif" width="241" height="10" />
																	</td>
																</tr>
																<tr>
																	<td colspan="2"
																		style="padding-top:10px; text-align:center"><a
																		href="register.jsp"><img name="image"
																			src="images/signupbutton.gif" width="135" height="33" />
																	</a></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</div></td>
										<td style="text-align:left; padding-top:10px; width:30%"><h1>您还没有注册？</h1>
											<p>注册新会员，看到不一样的世界!</p>
											<p>更详细的设备信息，注册即享受丰富折扣和优惠。</p>
											<p style="text-align:right">
												<a href="register.jsp"><img
													src="images/signupbutton.gif" width="135" height="33" /> </a>
											</p>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>



	<jsp:include page="jspComponent/foot.jsp" />


</body>
</html>
