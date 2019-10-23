function showErrorText(MapData,choodeElement)
{
	if(MapData != null)
	{
		var temp = "<font color='red'>"+MapData+"</font>";
		var errorText = document.getElementById(choodeElement);
		errorText.innerHTML = temp;
	}
	else
	{
		var errorText = document.getElementById(choodeElement);
		errorText.innerHTML = "     ";
	}
}



function send_ajax(formName,handleSucc,handleError)
{
	//获取请求路径
	var targetUrl = $("#"+formName).attr("action");
	alert("targetUrl:  "+targetUrl);
	//alert(targetUrl); 
	//数据序列化 
	var data = $("#"+formName).serialize();
	//alert("data: "+data);
	$.ajax({
		type:"POST",
		url:targetUrl,
		cache:false,
		dataType : "json",
		data:data,
		success:function(data){ 
 			handleSucc(data);
 		},
 		error:function(data){ 
 			handleError(data);
 		}
	});
}