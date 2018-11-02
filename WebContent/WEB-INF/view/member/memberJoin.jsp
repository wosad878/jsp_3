<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../../../temp/bootStrap.jsp"/>
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$("#btn").click(function(){
			var id = document.frm.id.value;
			/* var id = $("#id").val(); */
				window.open("./memberCheckId.do?id="+id, "", "width=300,height=200,top=300,left=700");
		});
	});
</script>
</head>
<body>
<c:import url="../../../temp/header.jsp"/>
<div class="container-fluid">
	
	<div class="row">
		 <form action="./memberJoin.do" name="frm" method="post" enctype="multipart/form-data">
		    <div class="form-group">
		      <label for="id">ID:</label>
		      <input type="text" class="form-control" id="id" placeholder="Enter ID" name="id">
		      <input type="button" id="btn" value="중복확인"> 
		    </div>
		    <div class="form-group">
		      <label for="pw">Passwrod:</label>
		      <input type="password" class="form-control" id="pw" placeholder="Enter Password" name="pw">
		    </div>
		     <div class="form-group">
		      <label for="name">Name:</label>
		      <input type="text" class="form-control" id="name" placeholder="Enter Name" name="name">
		    </div>
		    <div class="form-group">
		      <label for="email">Email:</label>
		      <input type="email" class="form-control" id="email" placeholder="Enter Email" name="email">
		    </div>
		    <div class="form-group">
		   	  <label class="radio-inline"> <input type="radio" name="kind" value="S">학생</label>
			  <label class="radio-inline"> <input type="radio" name="kind" value="T">교직원</label>
			</div>
		     <div class="form-group">
		      <label for="classmate">Classmate:</label>
		      <input type="text" class="form-control" id="classmate" placeholder="Enter ClassMate" name="classmate">
		    </div>
		    <div class="form-group">
		      <label for="file">File:</label>
		      <input type="file" class="form-control" id="file" name="f1">
		    </div>
		    <div class="form-group">
		      <button type="submit" class="btn btn-default">Write</button>
		    </div>
		  </form>
		
	</div>
</div>

<c:import url="../../../temp/footer.jsp"/>
</body>
</html>