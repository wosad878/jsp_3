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
	$("#btn").click(function(){
		$.ajax({
			url:"../a/memberInfo.do",
			type:"GET",
			data:{
				id:"t1",
				pw:"t1",
				kind:"T"
			},
			success:function(data){
				data = data.trim();
				data = JSON.parse(data);
				alert(data.id);
				alert(data.name);
				alert(data.email);
				
			}
		});
	});
	
	$("#id").blur(function(){
		var id = $(this).val();
		$.ajax({
			url:"../a/memberCheckId2.do",
			type:"post",
			data:{
				id:id
			},
			success:function(data){
				data = data.trim();
				data = JSON.parse(data);
				/* alert(data.result);
				alert(data.m); */
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