<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html public "-//w3c//dtd html 4.01 transitional//en" "http://www.w3.org/tr/html4/loose.dtd">


<html>
<head>
<meta http-equiv="content-language" content="zh-cn">
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/afterlogin/css/style.css" type="text/css" rel="stylesheet">
</head>

<body>
	<form 	 id="useraction_save_do" name="Form1
			"action="${pageContext.request.contextPath }/servlet/equipment?method=addEquipment" 
			enctype="multipart/form-data"
			method="post">
		&nbsp;
		<table cellspacing="1" cellpadding="5" width="100%" align="center"
			bgcolor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgcolor="#afd1f3" colspan="4"
					height="26"><strong><strong>添加设备</strong> </strong>
				</td>
			</tr>


			<tr>
				<td align="center" bgcolor="#f5fafe" class="ta_01">设备名称：</td>
				<td class="ta_01" bgcolor="#ffffff">
					<input type="text" name="equip_name" class="bg"/>
				</td>
				<td align="center" bgcolor="#f5fafe" class="ta_01">设备状态：</td>
				<td class="ta_01" bgcolor="#ffffff">
					<select name="state" id="state">
							<option value="" selected="selected">--选择设备状态--</option>
							<option value="working">wroking</option>
							<option value="ended">end</option>
							<option value="broken">broken</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center" bgcolor="#f5fafe" class="ta_01">设备数量：</td>
				<td class="ta_01" bgcolor="#ffffff"><input type="text"
					name="equipment_num" 
					class="bg" />
				</td>
				<td align="center" bgcolor="#f5fafe" class="ta_01">设备类别：</td>
				<td class="ta_01" bgcolor="#ffffff">
					<select name="category" id="category">
							<option value="" selected="selected">--选择设备类别--</option>
							<option value="嵌入式设备">嵌入式设备</option>
							<option value="计算机外设">计算机外设</option>
							<option value="移动设备">移动设备</option>
							<option value="服务设备">服务设备</option>
							<option value="其他">其他</option>
					</select>
				</td>
			</tr>


			<tr>
				<td align="center" bgcolor="#f5fafe" class="ta_01">设备图片：</td>
				<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<input
					type="file" name="upload" size="30" value=""/>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgcolor="#f5fafe">设备描述：</td>
				<td class="ta_01" bgcolor="#ffffff" colspan="3">
				<textarea name="description" cols="30" rows="3"  style="width: 96%"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="4" class="sep1">
				<img src="${pageContext.request.contextPath }/afterlogin/images/shim.gif">
				</td>
			</tr>


			<tr>
				<td class="ta_01" style="width: 100%" align="center"
					bgcolor="#f5fafe" colspan="4">
					
					
						
					<input type="submit" class="button_ok" value="确定">	
						
					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
					
					
					
					<input type="reset" value="重置" class="button_cancel">

					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font> <input
					class="button_ok" type="button" onclick="history.go(-1)" value="返回" />
					<span id="label1">
					
					</span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>