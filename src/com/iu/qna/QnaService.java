package com.iu.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.ActionFoward;
import com.iu.board.BoardDTO;
import com.iu.board.BoardService;
import com.iu.page.MakePager;
import com.iu.page.Pager;
import com.iu.page.RowNumber;

public class QnaService implements BoardService{

	@Override
	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionFoward update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionFoward delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private QnaDAO qnaDAO;
	
	public QnaService() {
		qnaDAO = new QnaDAO();
	}
	
	public void reply() {
		
	}
	public void replyUpdate() {
		
	}
	public ActionFoward selectOne(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		actionFoward.setCheck(false);
		actionFoward.setPath("./qnaList.do");
		BoardDTO boardDTO = null;
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			boardDTO = qnaDAO.selectOne(num);
			// 파일만들기
			// =========
			request.setAttribute("dto", boardDTO);
			actionFoward.setPath("../WEB-INF/qna/qnaSelectOne.jsp");
			actionFoward.setCheck(true);
		} catch (Exception e) {
			
		}
		if(boardDTO == null) {
			actionFoward.setCheck(false);
			actionFoward.setPath("./qnaList.do");
		}
		
		return actionFoward;
	}
	
	public ActionFoward selectList(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		int curPage = 1;
		try {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		MakePager mk = new MakePager(curPage, search, kind);
		RowNumber rowNumber = mk.makeRow();
		try {
			List<BoardDTO> ar = qnaDAO.selecList(rowNumber);
			int totalCount = qnaDAO.getCount(rowNumber.getSearch());
			Pager pager = mk.MakePage(totalCount);
			request.setAttribute("list", ar);
			request.setAttribute("pager", pager);
			request.setAttribute("board", "qna");
			actionFoward.setPath("../WEB-INF/view/board/boardList.jsp");
		} catch (Exception e) {
			request.setAttribute("message", "Fail");
			request.setAttribute("path","../index.jsp");
			actionFoward.setPath("../WEB-INF/common/result.jsp");
		}
		actionFoward.setCheck(true);
		return actionFoward;
	}
}
