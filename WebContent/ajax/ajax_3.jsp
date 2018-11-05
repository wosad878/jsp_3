<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/bootStrap.jsp"/>
<script type="text/javascript">
	$(function(){
		$("#btn").click(function(){
			var id =$("#id").val();
			/* $.get("../member/memberCheckId.do?id="+id, function(data){
				$("#result").html(data);
			}); */
			$.post("../member/memberCheckId.do",, function(data){
				$("#result").html(data);
			});
		});
		
	});
</script>
</head>
<body>
<h1>$.GET</h1>
<input type="text" id="id">
<button id="btn">CLICK</button>
<div id="result"></div>
</body>
</html>