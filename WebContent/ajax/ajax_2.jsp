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
			var board = "";
			$(".kind").each(function(){
				if($(this).prop("checked")){
					board=$(this).val();
				}
			});
			$("#result").load("../"+board+"/"+board+"Write.do");
		});
	});
</script>
</head>
<body>
	<h1>Jquery Ajax</h1>
	NOTICE<input type="radio" value="notice" name="kind" class="kind">
	QNA<input type="radio" value="qna" name="kind" class="kind">
	<button id="btn">Click</button>
	<div id="result"></div>
</body>
</html>