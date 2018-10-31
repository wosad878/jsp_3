<%@page import="java.util.ArrayList"%>
<%@page import="com.iu.notice.NoticeDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.iu.notice.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>    
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- Theme Made By www.w3schools.com - No Copyright -->
  <title>Bootstrap Theme Company Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="../temp/bootStrap.jsp"></jsp:include>	
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

<jsp:include page="../temp/header.jsp"></jsp:include>

<div class="container-fluid">
	<div class="row">
		 <form action="./noticeWriteProcess.jsp" method="post" enctype="multipart/form-data">
		    <div class="form-group">
		      <label for="title">Title:</label>
		      <input type="text" class="form-control" id="title" placeholder="Enter Title" name="title">
		    </div>
		    <div class="form-group">
		      <label for="writer">Writer:</label>
		      <input type="text" class="form-control" id="writer" placeholder="Enter Writer" name="writer">
		    </div>
		    <div class="form-group">
		      <label for="contents">Contents:</label>
		      <textarea rows="25" cols="" class="form-control" name="contents"></textarea>
		    </div>
		    
		    <div class="form-group">
		      <label for="file">File:</label>
		      <input type="file" class="form-control" id="file" name="f1">
		    </div>
		    <div class="form-group">
		      <label for="file">File:</label>
		      <input type="file" class="form-control" id="file" name="f2">
		    </div>
		    
		    
		    <button type="submit" class="btn btn-default">Write</button>
		  </form>
	
		
	</div>
</div>
	

<jsp:include page="../temp/footer.jsp"></jsp:include>

</body>
</html>
