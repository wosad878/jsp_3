package com.iu.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.ActionFoward;

public interface BoardService {
	
	//list
	public ActionFoward selectList(HttpServletRequest request, HttpServletResponse response);
	
	//selectOne
	public ActionFoward selectOne(HttpServletRequest request, HttpServletResponse response);

	//insert
	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response);
	
	//update
	public ActionFoward update(HttpServletRequest request, HttpServletResponse response);
	
	//delete
	public ActionFoward delete(HttpServletRequest request, HttpServletResponse response);
	
	
}








