<%@page import="java.util.ArrayList"%>
<%@page import="com.iu.notice.NoticeDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.iu.notice.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- Theme Made By www.w3schools.com - No Copyright -->
  <title>Bootstrap Theme Company Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="../../../temp/bootStrap.jsp"></jsp:include>	
<script src="https://cdn.ckeditor.com/4.10.1/full/ckeditor.js"></script>
<script type="text/javascript">
	$(function(){
		CKEDITOR.replace("contents");
		
		$("#btn").click(function(){
			var title = $("#title").val();
			if(title != ''){
				$("#frm").submit();
			}else{
				alert("제목");
			}
		});
		var count = 1;
		var index = 0;
		$("#addbtn").click(function(){
			if(count < 6){
				var r='<div class="form-group" id="f'+index+'">';
				r = r+'<label for="file">File:</label>';
				r = r+'<input type="file" class="form-control" id="file" name="f'+count+'">';
				r = r+'<span class="remove" title="'+index+'">X</span>';
				r = r+'</div>';
				$('#file').append(r);
				count++;
				index++;
			}else{
				alert("5개");
			}
			
		});
		$("#file").on("click", ".remove", function(){
			var t = $(this).attr("title");
			$("#f"+t).remove();
			count--;
		});
	});
</script>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

<jsp:include page="../../../temp/header.jsp"></jsp:include>

<div class="container-fluid">
	<div class="row">
		 <form id="frm" action="./${board}Write.do" method="post" enctype="multipart/form-data">
		    <div class="form-group">
		      <label for="title">Title:</label>
		      <input type="text" class="form-control" id="title" placeholder="Enter Title" name="title">
		    </div>
		    <div class="form-group">
		      <label for="writer">Writer:</label>
		      <input type="text" class="form-control" readonly="readonly" id="writer" placeholder="Enter Writer" name="writer" value="${member.id}">
		    </div>
		    <div class="form-group">
		      <label for="contents">Contents:</label>
		      <textarea rows="25" cols="" class="form-control" name="contents"></textarea>
		    </div>
			<input type="button" value="File add"class="btn btn-default" id="addbtn">		
		    
		   <!--  <div class="form-group">
		      <label for="file">File:</label>
		      <input type="file" class="form-control" id="file" name="f1">
		    </div> -->
			<div class="files" id="file">
			
			</div>    
		    <input type="button" value="write" class="btn btn-default" id="btn">
		  </form>
	
		
	</div>
</div>
	

<jsp:include page="../../../temp/footer.jsp"></jsp:include>

</body>
</html>
