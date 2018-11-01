package com.iu.member;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.iu.action.ActionFoward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberService {
	
	private MemberDAO memberDAO;
	private ActionFoward actionFoward;
	
	public MemberService() {
		memberDAO = new MemberDAO();
	}
	
	public ActionFoward update(HttpServletRequest request, HttpServletResponse response) {
		actionFoward = new ActionFoward();
		String method = request.getMethod();
		String message ="실패";
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
				MemberDTO sessionDTO = (MemberDTO)request.getSession().getAttribute("member");
				memberDTO.setId(multi.getParameter("id"));
				memberDTO.setPw(multi.getParameter("pw"));
				memberDTO.setName(multi.getParameter("name"));
				memberDTO.setEmail(multi.getParameter("email"));
				memberDTO.setFname(multi.getParameter("f1"));
				memberDTO.setOname(multi.getParameter("f1"));
				memberDTO.setKind(sessionDTO.getKind());
				memberDTO.setClassmate(sessionDTO.getClassmate());
				file = multi.getFile("f1");
				if(file != null) {
					file = new File(path, sessionDTO.getFname());
					file.delete();
					memberDTO.setFname(multi.getFilesystemName("f1"));
					memberDTO.setOname(multi.getOriginalFileName("f1"));
				}
				int result = memberDAO.update(memberDTO);
				if(result > 0) {
					request.getSession().setAttribute("member", memberDTO);
					message = "성공";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("path", "./memberMyPage.do");
			request.setAttribute("message", message);
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/common/result.jsp");
		}else {
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/member/memberUpdate.jsp");
		}
		return actionFoward;
	}
	
	public ActionFoward delete(HttpServletRequest request, HttpServletResponse response) {
		actionFoward = new ActionFoward();
		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		String id = memberDTO.getId();
		try {
			int result = memberDAO.delete(id);
			if(result > 0) {
				String path = session.getServletContext().getRealPath("upload");
				File file = new File(path, memberDTO.getFname());
				file.delete();
				request.setAttribute("message", "삭제성공");
				session.invalidate();
			}else {
				request.setAttribute("message", "삭제실패");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/result.jsp");
		request.setAttribute("path", "../index.jsp");
		
		return actionFoward;
	}
	
	public ActionFoward myPage(HttpServletRequest request, HttpServletResponse response) {
		actionFoward = new ActionFoward();
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/member/memberMyPage.jsp");
		
		return actionFoward;
	}
	
	public ActionFoward logout(HttpServletRequest request, HttpServletResponse response){
		actionFoward = new ActionFoward();
		HttpSession session = request.getSession();
		session.invalidate();
		actionFoward.setCheck(false);
		actionFoward.setPath("../index.jsp");
		return actionFoward;
	}
	
	public ActionFoward memberLogin(HttpServletRequest request, HttpServletResponse response) {
		String method = request.getMethod();
		actionFoward = new ActionFoward();
		if(method.equals("POST")) {
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setId(request.getParameter("id"));
			memberDTO.setPw(request.getParameter("pw"));
			memberDTO.setKind(request.getParameter("kind"));
			
			try {
				memberDTO = memberDAO.login(memberDTO);
				HttpSession session = request.getSession();
				session.setAttribute("member", memberDTO);
			} catch (Exception e) {
				memberDTO = null;
				e.printStackTrace();
			}
			if(memberDTO != null) {
				actionFoward.setCheck(false);
				actionFoward.setPath("../index.jsp");
			}else {
				request.setAttribute("message", "로그인 실패");
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/member/memberLogin.jsp");
			}
		}else {
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/member/memberLogin.jsp");
		}
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
				//파일정보를 DTO에 추가
				memberDTO.setFname(multi.getFilesystemName("f1"));
				memberDTO.setOname(multi.getOriginalFileName("f1"));
		
				int result = memberDAO.join(memberDTO);
				
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



/*public ActionFoward memberLogin(HttpServletRequest request, HttpServletResponse response) {
	actionFoward = new ActionFoward();
	String message ="";
	String path ="";
	String method = request.getMethod();
	MemberDTO memberDTO = new MemberDTO();
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
}*/
