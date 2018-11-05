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
		$("#btn3").click(function(){
			 var title=$("#title").val();
			 var writer=$("#writer").val();
			 var contents=$("#contents").val();
			 var xhp = new XMLHttpRequest();
			 xhp.open("POST", "../notice/noticeWrite.do");
			 xhp.send();
		 	 xhp.onreadystatechange = function(){
				if(this.readyState == 4 && this.status == 200){
					$("#result").html(this.responseText);
				}
			};
		});
		$("#btn2").click(function(){
			var num = $("#num").val();
			var xhp = new XMLHttpRequest();
			xhp.open("GET", "../notice/noticeDelete.do?num="+num);
			xhp.send("title="+title+"&writer="+writer+"&contents="+contents);
			xhp.onreadystatechange = function(){
				if(this.readyState == 4 && this.status == 200){
					$("#result").html(this.responseText);
				}
			};
		});		
		
		$("#btn").click(function(){
			var num = $("#num").val();
			/* alert(num); */
			var xhp = new XMLHttpRequest();
			xhp.open("GET","../notice/noticeSelectOne.do?num="+num);
			xhp.send();
			xhp.onreadystatechange = function(){
				if(this.readyState == 4 && this.status == 200){
					$("#result").html(this.responseText);
				}
			};
		});
	});
</script>
</head>
<body>
<h1>AJAX TEST</h1>
	<div><input type="text" id="writer"></div>
	<div><input type="text" id="title"></div>
	<textarea id="contents" rows=""	col="25"></textarea>
	
	<input type="number" id="num">
	<input type="button" id="btn" value="click">
	<input type="button" id="btn2" value="DELETE">
	<div id="result">
		
	</div>
</body>
</html>