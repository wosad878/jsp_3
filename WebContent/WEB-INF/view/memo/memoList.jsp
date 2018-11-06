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
				$.get("./memoMore.do",function(data){
					$("#table").html(data);
				});
			});
		});
		
		$("#del").click(function() {
			var p = "?";
			$(".del").each(function() {
				if ($(this).prop("checked")) {
					p=p+"num="+$(this).val()+"&";
					/* $.get("./memoDelete.do?num=" + $(this).val()) */
					}
				});
			$.get("./memoDelete.do"+p,function(){
			$.get("./memoMore.do", function(data) {
				$("#table").html(data.trim());
				});
			});
		});
		var curPage = 1;
		$("#more").click(function() {
			curPage++;
			$.get("./memoMore.do?curPage=" + curPage, function(data) {
				$("#table").append(data);

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
		<tbody id="table">
			<tr>
				<td></td>
				<td>NUM</td>
				<td>CONTENTS</td>
				<td>WRITER</td>
				<td>DATE</td>
			</tr>
			<c:forEach items="${list}" var="m">
			<tr>
				<td><input type="checkbox" name="del" class="del" value="${m.num}" id="${m.num}"></td>
				<td>${m.num}</td>
				<td>${m.contents}</td>
				<td>${m.writer}</td>
				<td>${m.reg_date}</td>
			</tr>
			</c:forEach>
		</tbody>	
		</table>
		<button id="more">더보기</button>
	</div>
	<div class="row">
		<button id="del">DEL</button>
	</div>
</div>

<c:import url="../../../temp/footer.jsp"/>
</body>
</html>