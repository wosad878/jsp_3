package com.iu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.ActionFoward;
import com.iu.notice.NoticeService;

/**
 * Servlet implementation class NoticeController
 */
@WebServlet("/NoticeController")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//  /notice/notice***.do
		//  /notice***.do
		String command = request.getPathInfo();
		
		//foward, redirect
		ActionFoward actionFoward=null;
		NoticeService noticeService = new NoticeService();
		
		if(command.equals("/noticeList.do")) {
			actionFoward = noticeService.selectList(request, response);
		}else if(command.equals("/noticeSelectOne.do")) {
			actionFoward = noticeService.selectOne(request, response);
		}else if(command.equals("/noticeWrite.do")) {
			actionFoward = noticeService.insert(request, response);
		}else if(command.equals("/noticeDelete.do")) {
			actionFoward = noticeService.delete(request, response);
		}else if(command.equals("/noticeUpdate.do")) {
			actionFoward = noticeService.update(request, response);
		}
		
		
		
		if(actionFoward.isCheck()) {
			RequestDispatcher view = request.getRequestDispatcher(actionFoward.getPath());
			view.forward(request, response);
		}else {
			response.sendRedirect(actionFoward.getPath());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
