package com.iu.member;

import java.io.File;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.ActionFoward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberService {
	
	private MemberDAO memberDAO;
	private ActionFoward actionFoward;
	
	public MemberService() {
		memberDAO = new MemberDAO();
	}
	
	public ActionFoward memberLogin(HttpServletRequest request, HttpServletResponse response) {
		actionFoward = new ActionFoward();
		String message ="";
		String path ="";
		String method = request.getMethod();
		MemberDTO memberDTO = null;
		Cookie[] cookies = request.getCookies();
		
		if(method.equals("POST")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String kind = request.getParameter("kind");
					
			
				try {
					memberDTO = memberDAO.selectOne(id, pw, kind);
					if(memberDTO != null) {
						message = "로그인 성공";
						path="./memberSelectOne.do";
						Cookie cookie = new Cookie("id", memberDTO.getId());
						cookie.setMaxAge(60*60);
						response.addCookie(cookie);
						actionFoward.setCheck(true);
						actionFoward.setPath("../WEB-INF/view/common/result.jsp");
					}else {
						message = "로그인 실패";
						path = "./memberLogin.do";
						actionFoward.setCheck(true);
						actionFoward.setPath("../WEB-INF/view/common/result.jsp");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else {
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/member/memberLogin.jsp");
		}
			request.setAttribute("message", message);
			request.setAttribute("path", path);
		
		return actionFoward;
	}
	
	public ActionFoward memberJoin(HttpServletRequest request, HttpServletResponse response) {
		actionFoward = new ActionFoward();
		String method = request.getMethod();
		if(method.equals("POST")) {
			int max = 1024*1024*10;
			String path = request.getServletContext().getRealPath("upload");
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			try {
				MultipartRequest multi = new MultipartRequest(request, path, max, "utf-8", new DefaultFileRenamePolicy());
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setId(multi.getParameter("id"));
				memberDTO.setPw(multi.getParameter("pw"));
				memberDTO.setName(multi.getParameter("name"));
				memberDTO.setEmail(multi.getParameter("email"));
				memberDTO.setKind(multi.getParameter("kind"));
				memberDTO.setClassmate(multi.getParameter("classmate"));
		
				int result = memberDAO.join(memberDTO);
				//파일정보를 DB에 insert
				
				if(result > 0) {
					request.setAttribute("message", "가입성공");
					request.setAttribute("path", "../index.jsp");
				}else {
					request.setAttribute("message", "가입실패");
					request.setAttribute("path", "./memberJoin.do");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/common/result.jsp");
		}else {
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/member/memberJoin.jsp");
		}
		return actionFoward;
	}
}
