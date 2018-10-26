<%@page import="com.iu.page.Pager"%>
<%@page import="com.iu.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.iu.page.MakePager"%>
<%@page import="com.iu.page.RowNumber"%>
<%@page import="com.iu.notice.NoticeDAO"%>
<%@page import="com.iu.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	int curPage = 1;
	try{
		curPage = Integer.parseInt(request.getParameter("curPage"));
	}catch(Exception e){
		
	}
	String kind = request.getParameter("kind");
	if(kind == null || kind.equals("")){
		kind = "Title";
	}
	String search = request.getParameter("search");
	if(search == null){
		search = "";
	}
	BoardDAO boardDAO = new NoticeDAO();
	MakePager mk = new MakePager(curPage, search, kind);
	List<BoardDTO> ar =  boardDAO.selecList(mk.makeRow());
	int totalCount = boardDAO.getCount(kind, search);
	//page
	Pager pager = mk.MakePage(totalCount);
	
	request.setAttribute("list", ar);
	request.setAttribute("board", "notice");
	request.setAttribute("pager", pager);
	RequestDispatcher view = request.getRequestDispatcher("../board/boardList.jsp");
	view.forward(request, response);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>