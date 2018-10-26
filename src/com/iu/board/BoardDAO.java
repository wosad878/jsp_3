package com.iu.board;

import java.util.List;

public interface BoardDAO {

	//selectList
	public List<BoardDTO> selecList(int startRow, int lastRow, String kind, String search) throws Exception;
	//selectOne
	public BoardDTO selectOne(int num) throws Exception;
	//insert
	public int insert(BoardDTO boardDTO) throws Exception;
	//update
	public int update(BoardDTO boardDTO) throws Exception;
	//delete
	public int delete(int num) throws Exception;
	//getCount
	public int getCount(String kind, String search) throws Exception;
}
