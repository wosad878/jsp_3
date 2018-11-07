<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/bootStrap.jsp"/>
<script type="text/javascript">

$(function(){
	$("#id").blur(function(){
		var id = $(this).val();
		$.ajax({
			url:"../a/memberCheckI.do",
			type:"post",
			data:{
				id:id
			},
			success:function(data){
				data = data.trim();
				if(data =='2'){
					$("#result").html("<h1>사용가능</h1>");
				}else{
					$("#result").html("<h1>사용불가능</h1>");
					$("#id").val('');
				}
			},error:function(){
				alert("error");
				$("#id").val('');
			}
			
		});
	});
});
</script>
</head>
<body>

<input type="text" id="id">
<button id="btn">CLICK</button>
<div id="result"></div>
</body>
</html>