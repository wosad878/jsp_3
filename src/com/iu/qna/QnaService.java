package com.iu.qna;

import java.io.File;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.ActionFoward;
import com.iu.board.BoardDTO;
import com.iu.board.BoardService;
import com.iu.file.FileDAO;
import com.iu.file.FileDTO;
import com.iu.page.MakePager;
import com.iu.page.Pager;
import com.iu.page.RowNumber;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class QnaService implements BoardService{
	private QnaDAO qnaDAO;
	
	public QnaService() {
		qnaDAO = new QnaDAO();
	}
	
	@Override
	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response) {
		String message = "Fail";
		String path = "./qnaList.do";
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();
		if(method.equals("POST")) {
			int maxSize = 1024* 1024* 100;
			String save = request.getServletContext().getRealPath("upload");
			File file = new File(save);
			if(!file.exists()) {
				file.mkdirs();
			}
			try {
				MultipartRequest multi = new MultipartRequest(request, save, maxSize, "utf-8", new DefaultFileRenamePolicy());
				QnaDTO qnaDTO = new QnaDTO();
				qnaDTO.setTitle(multi.getParameter("title"));
				qnaDTO.setWriter(multi.getParameter("writer"));
				qnaDTO.setContents(multi.getParameter("contents"));
				qnaDTO.setNum(qnaDAO.getNum());
				int result = qnaDAO.insert(qnaDTO);
				System.out.println(result);
				if (result > 0) {
					FileDAO fileDAO = new FileDAO();
					Enumeration<Object> e = multi.getFileNames();
					while(e.hasMoreElements()) {
						String p = (String)e.nextElement();
						FileDTO fileDTO = new FileDTO();
						fileDTO.setKind("Q");
						fileDTO.setNum(qnaDAO.getNum());
						fileDTO.setFname(multi.getFilesystemName(p));
						fileDTO.setOname(multi.getOriginalFileName(p));
						fileDAO.insert(fileDTO);
					}
					message = "Success";
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
				}else {
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("에러");
				// TODO: handle exception
			}
			request.setAttribute("message", message);
			request.setAttribute("path", path);
		}else {
			request.setAttribute("board", "qna");
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/board/boardWrite.jsp");
		}
		return actionFoward;
	}



	@Override
	public ActionFoward update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ActionFoward delete(HttpServletRequest request, HttpServletResponse response) {
		String message = "Fail";
		String path = "./qnaList.do";
		int num = Integer.parseInt(request.getParameter("num"));
		ActionFoward actionFoward = new ActionFoward();
		try {
			int result = qnaDAO.delete(num);
			if(result > 0) {
				message = "Success";
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/common/result.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		request.setAttribute("message", message);
		request.setAttribute("path", path);
		return actionFoward;
	}



	public ActionFoward selectOne(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		actionFoward.setCheck(false);
		actionFoward.setPath("./qnaList.do");
		BoardDTO boardDTO=null;
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			boardDTO = qnaDAO.selectOne(num);
			
			request.setAttribute("dto", boardDTO);
			request.setAttribute("board", "qna");
			actionFoward.setPath("../WEB-INF/view/board/boardSelectOne.jsp");
			actionFoward.setCheck(true);
		}catch (Exception e) {
			// TODO: handle exception
		}
		if(boardDTO == null) {
			actionFoward.setCheck(false);
			actionFoward.setPath("./qnaList.do");
		}
		
		return actionFoward;
	}
	
	//list
	public ActionFoward selectList(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		int curPage=1;
		try {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}catch (Exception e) {
			
		}
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		
		MakePager mk = new MakePager(curPage, search, kind);
		RowNumber rowNumber = mk.makeRow();
		List<BoardDTO> ar;
		try {
			ar = qnaDAO.selectList(rowNumber);
			int totalCount = qnaDAO.getCount(rowNumber.getSearch());
			Pager pager = mk.makePage(totalCount);
			request.setAttribute("list", ar);
			request.setAttribute("pager", pager);
			request.setAttribute("board", "qna");
			actionFoward.setPath("../WEB-INF/view/board/boardList.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		actionFoward.setCheck(true);
		return actionFoward;
	}

}
