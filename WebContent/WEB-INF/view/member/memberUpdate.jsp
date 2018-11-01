<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../../../temp/bootStrap.jsp"/>
<title>Insert title here</title>
</head>
<body>
<c:import url="../../../temp/header.jsp"/>
<div class="container-fluid">
	
	<div class="row">
		 <form action="./memberUpdate.do" method="post" enctype="multipart/form-data">
		     <div class="form-group">
		      <label for="id">ID:</label>
		      <input type="text" class="form-control" readonly="readonly" id="id" placeholder="Enter ID" name="id" value="${member.id}">
		    </div>
		    <div class="form-group">
		      <label for="pw">Passwrod:</label>
		      <input type="password" class="form-control" id="pw" placeholder="Enter Password" name="pw" value="${member.pw}">
		    </div>
		     <div class="form-group">
		      <label for="name">Name:</label>
		      <input type="text" class="form-control" id="name" placeholder="Enter Name" name="name" value="${member.name}">
		    </div>
		    <div class="form-group">
		      <label for="email">Email:</label>
		      <input type="email" class="form-control" id="email" placeholder="Enter Email" name="email" value="${member.email}">
		    </div>
		    <div class="form-group">
		      <label for="file">File:</label>
		      <input type="file" class="form-control" id="file" name="f1">
		    </div>
		    <div class="form-group">
		      <button type="submit" class="btn btn-default">Update</button>
		    </div>
		  </form>
		
	</div>
</div>

<c:import url="../../../temp/footer.jsp"/>
</body>
</html>