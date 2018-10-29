package com.iu.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.iu.board.BoardDAO;
import com.iu.board.BoardDTO;
import com.iu.board.BoardReply;
import com.iu.board.BoardReplyDTO;
import com.iu.page.RowNumber;
import com.iu.page.Search;
import com.iu.util.DBConnector;

public class QnaDAO implements BoardDAO, BoardReply {

	@Override
	public int reply(BoardReplyDTO boardReplyDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int replyUpdate(BoardReplyDTO boardReplyDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardDTO> selecList(RowNumber rowNumber) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select * from "
				+ "(select rownum r, q.* from "
				+ "(select num, title, writer, reg_date, hit from qna "
				+ "where "+rowNumber.getSearch().getKind()+" like ? "
				+ "order by num desc) q) "
				+ "where r between? and ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+rowNumber.getSearch().getSearch()+"%");
		st.setInt(2, rowNumber.getStartRow());
		st.setInt(3, rowNumber.getLastRow());
		
				
		return null;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int num) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCount(Search search) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
