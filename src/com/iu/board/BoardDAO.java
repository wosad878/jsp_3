package com.iu.board;

import java.util.List;

import com.iu.page.RowNumber;
import com.iu.page.Search;

public interface BoardDAO {

	//selectList
	public List<BoardDTO> selecList(RowNumber rowNumber) throws Exception;
	//selectOne
	public BoardDTO selectOne(int num) throws Exception;
	//insert
	public int insert(BoardDTO boardDTO) throws Exception;
	//update
	public int update(BoardDTO boardDTO) throws Exception;
	//delete
	public int delete(int num) throws Exception;
	//getCount
	public int getCount(Search search) throws Exception;
}
