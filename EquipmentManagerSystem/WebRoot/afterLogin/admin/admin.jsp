<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*,com.tianfu.domain.Equipment" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.request.contextPath}/afterlogin/admin/css/style.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/afterlogin/admin/js/public.js">
	</script>
<script type="text/javascript">
	function addproduct() {
		window.location.href = "${pageContext.request.contextPath}/afterLogin/addEquiment.jsp";
	}
</script>
</head>
<body>
	<br>
	<form id="form1" name="form1"
		action="${pageContext.request.contextPath}/servlet/equipment?method=findEquipmentBymanyCondition"
		method="post">
		<table cellspacing="1" cellpadding="0" width="100%" align="center"
			bgcolor="#f5fafe" border="0">
			<tbody>
				<tr>
					<td class="ta_01" align="center" bgcolor="#afd1f3"><strong>查
							询 条 件</strong></td>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="22" align="center" bgcolor="#f5fafe" class="ta_01">
									设备名字：</td>
								<td class="ta_01" bgcolor="#ffffff"><input type="text"
									name="name" class="bg" size="15" placeholder="请输入设备名字" /></td>
								<td height="22" align="center" bgcolor="#f5fafe" class="ta_01">
									设备状态：</td>
								<td class="ta_01" bgcolor="#ffffff"><select name="state"
									id="state">
										<option value="" selected="selected">--选择设备状态--</option>
										<option value="working">wroking</option>
										<option value="end">end</option>
										<option value="broken">broken</option>
								</select></td>
							</tr>

							<tr>
								<td height="22" align="center" bgcolor="#f5fafe" class="ta_01">
									登录时间：</td>
								<td class="ta_01" bgcolor="#ffffff"><input type="text"
									name="login_time" class="bg" size="15" placeholder="请输入设备登录时间" />
								</td>
								<td height="22" align="center" bgcolor="#f5fafe" class="ta_01">
									设备状态：</td>
								<td class="ta_01" bgcolor="#ffffff"><input type="text"
									name="logout_time" class="bg" size="15" placeholder="请输入设备登出时间" />
								</td>
							</tr>

							<tr>
								<td width="100" height="22" align="center" bgcolor="#f5fafe"
									class="ta_01"></td>
								<td class="ta_01" bgcolor="#ffffff"><font face="宋体"
									color="red"> &nbsp;</font></td>
								<td align="right" bgcolor="#ffffff" class="ta_01"><br>
									<br></td>
								<td align="right" bgcolor="#ffffff" class="ta_01">
									<button type="submit" id="search" name="search" value="查询所有"
										class="button_view">
										<!-- &#26597;&#35810; -->
										&#26597;&#35810;
									</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="reset"
									name="reset" value="&#37325;&#32622;" class="button_view" />
								</td>
							</tr>
						</table>
					</td>

				</tr>
				<tr>
					<td class="ta_01" align="center" bgcolor="#afd1f3"><strong>设备列表</strong>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="right">
						<button type="button" id="add" name="add" value="&#28155;&#21152;"
							class="button_add" onclick="addproduct()">&#28155;&#21152;
						</button>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgcolor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="datagrid1"
							style="border-right: gray 1px solid; border-top: gray 1px solid; border-left: gray 1px solid; width: 100%; word-break: break-all; border-bottom: gray 1px solid; border-collapse: collapse; background-color: #f5fafe; word-wrap: break-word">
							<tr
								style="font-weight: bold; font-size: 12pt; height: 25px; background-color: #afd1f3">
								<td align="center" width="24%">设备名字</td>
								<td align="center" width="18%">设备登录时间</td>
								<td align="center" width="9%">设备登出时间</td>
								<td align="center" width="9%">设备状态</td>
								<td width="8%" align="center">设备相关文件</td>
								<td width="8%" align="center">编辑</td>

								<td width="8%" align="center">删除</td>
							</tr>

							<c:forEach items="${equipments}" var="equip">
								<tr onmouseover="this.style.backgroundcolor = 'white'"
									onmouseout="this.style.backgroundcolor = '#f5fafe';">
									<td style="cursor: hand; height: 22px" align="center"
										width="10%">${equip.name }</td>
									<td style="cursor: hand; height: 22px" align="center"
										width="15%">
										<fmt:formatDate value="${equip.login_time }" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>

									<td style="cursor: hand; height: 22px" align="center"
										width="15%">
										<fmt:formatDate value="${equip.logout_time }" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td style="cursor: hand; height: 22px" align="center"
										width="8%">${equip.state}</td>
									<td style="cursor: hand; height: 22px" align="center"
										width="8%"><a
										href="${pageContext.request.contextPath}/servlet/equipment?method=downloadFile&path=${equip.image_path}">下载文件</a>
									</td>
									<td align="center" style="height: 22px" width="7%">
										<a href="${pageContext.request.contextPath}/afterLogin/admin/editEquipment.jsp?name=${equip.name }">
											<img
												src="${pageContext.request.contextPath}/afterLogin/admin/images/i_edit.gif"
												border="0" style="cursor: hand">
										</a>
									</td>

									<td align="center" style="height: 22px" width="7%"><a
										href="${pageContext.request.contextPath}/servlet/equipment?method=deleteEquipment&name=${equip.name }"> <img
											src="${pagecontext.request.contextpath}/afterLogin/admin/images/i_del.gif"
											width="16" height="16" border="0" style="cursor: hand">
									</a></td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>

