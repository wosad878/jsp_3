package com.iu.memo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.ActionFoward;
import com.iu.page.MakePager;
import com.iu.page.RowNumber;

public class MemoService {
	
	private MemoDAO memoDAO;
	
	public MemoService() {
		memoDAO = new MemoDAO();
	}
	public ActionFoward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String message = "Fail";
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			int	result = memoDAO.delete(num);
			if(result > 0) {
				message = "Success";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute("message" , message);
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		return actionFoward;
	}
	
	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		MemoDTO memoDTO = new MemoDTO();
		memoDTO.setContents(request.getParameter("contents"));
		memoDTO.setWriter(request.getParameter("writer"));
		try {
			int result = memoDAO.insert(memoDTO);
			String message="Fail";
			if(result>0) {
				message ="Success";
			}
			request.setAttribute("message", message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		return actionFoward;
	}
	
	
	public ActionFoward selectList(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward  actionFoward= new ActionFoward();
		//1. curPage
		//2. kind, search
		int curPage = 1;
		try {
			curPage= Integer.parseInt(request.getParameter("curPage"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		MakePager makePager = new MakePager(curPage, "", "");
		RowNumber rowNumber = makePager.makeRow();
		try {
			List<MemoDTO> ar = memoDAO.selectList(rowNumber);
			request.setAttribute("list", ar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/memo/memoList.jsp");
		
		return actionFoward;
	}

}