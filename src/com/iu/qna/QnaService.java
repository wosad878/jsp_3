package com.iu.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.ActionFoward;
import com.iu.board.BoardDTO;
import com.iu.page.MakePager;
import com.iu.page.Pager;
import com.iu.page.RowNumber;

public class QnaService {

	private QnaDAO qnaDAO;
	
	public QnaService() {
		qnaDAO = new QnaDAO();
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
			actionFoward.setPath("../WEB-INF/qna/qnaList.jsp");
		} catch (Exception e) {
			request.setAttribute("message", "Fail");
			request.setAttribute("path","../index.jsp");
			actionFoward.setPath("../WEB-INF/common/result.jsp");
		}
		actionFoward.setCheck(true);
		return actionFoward;
	}
}
