<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container-fluid">
	<div class="row">
		<c:if test="${member.kind == 'T'}">
			<div class="col-md-1">
				<a href="./${board}Write.do" class="btn btn-primary">Write</a>
			</div>
		</c:if>
	</div>
</div>