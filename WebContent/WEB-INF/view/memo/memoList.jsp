<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/bootStrap.jsp"/>
<script type="text/javascript">
	$(function(){
		$("#write").click(function(){
			var writer = $("#writer").val();
			var contents = $("#contents").val();
			$.post("./memoWrite.do",{writer:writer, contents:contents},function(data){
				alert(data);
				location.reload();
			});
		});
		$("#del").click(function(){
			$(".del").each(function(){
				if($(this).prop("checked")){
				var num = $(this).attr("id");
				$.post("./memoDelete.do",{num:num},function(data){
					alert(data);
					location.reload();
				});
				}
			});
		});
	});
</script>
</head>
<body>
<c:import url="../../../temp/header.jsp"/>
<div class="container-fluid">
	<div class="row">
		 <div class="form-group">
		      <label for="writer">Writer:</label>
		      <input type="text" class="form-control" id="writer" placeholder="Enter Writer" name="writer" >
	     </div>
	    <div class="form-group">
	      <label for="contents">Contents:</label>
	      <textarea rows="25" cols="" class="form-control" id="contents" name="contents"></textarea>
	    </div>
		<input type="button" value="Write"class="btn btn-default" id="write">
	</div>
	<div class="row">
		<table class="table table-hover">
			<tr>
				<td></td>
				<td>NUM</td>
				<td>CONTENTS</td>
				<td>WRITER</td>
				<td>DATE</td>
			</tr>
			<c:forEach items="${list}" var="m">
			<tr>
				<td><input type="checkbox" name="del" class="del" id="${m.num}"></td>
				<td>${m.num}</td>
				<td>${m.contents}</td>
				<td>${m.writer}</td>
				<td>${m.reg_date}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="row">
		<button id="del">DEL</button>
	</div>
</div>

<c:import url="../../../temp/footer.jsp"/>
</body>
</html>