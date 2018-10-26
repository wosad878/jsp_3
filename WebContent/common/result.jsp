<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String message = (String)request.getAttribute("message");
	String path = (String)request.getAttribute("path");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	alert('<%=message%>');
	location.href="<%=path%>";
</script>
</head>
<body>

</body>
</html>