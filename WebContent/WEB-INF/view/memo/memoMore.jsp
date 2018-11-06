<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${list}" var="m">
	<tr>
		<td><input type="checkbox" name="del" class="del" id="${m.num}"></td>
		<td>${m.num}</td>
		<td>${m.contents}</td>
		<td>${m.writer}</td>
		<td>${m.reg_date}</td>
	</tr>
</c:forEach>