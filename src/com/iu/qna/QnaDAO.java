package com.iu.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
		Connection con = DBConnector.getConnect();
		String sql = "insert into qna values(qna_seq.nextval,?,?,?,sysdate,0,?,?,?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, boardReplyDTO.getTitle());
		st.setString(2, boardReplyDTO.getWriter());
		st.setString(3, boardReplyDTO.getContents());
		st.setInt(4, boardReplyDTO.getRef());
		st.setInt(5, boardReplyDTO.getStep());
		st.setInt(6, boardReplyDTO.getDepth());
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}

	@Override
	public int replyUpdate(BoardReplyDTO boardReplyDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "update qna set step=step+1 where ref=? and step>?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, boardReplyDTO.getRef());
		st.setInt(2, boardReplyDTO.getStep());
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}

	@Override
	public List<BoardDTO> selecList(RowNumber rowNumber) throws Exception {
		BoardDTO boardDTO = null;
		List<BoardDTO> ar = new ArrayList<>();
		Connection con = DBConnector.getConnect();
		String sql = "select * from "
				+ "(select rownum r, q.* from "
				+ "(select num, title, writer, contents, reg_date, hit from qna "
				+ "where "+rowNumber.getSearch().getKind()+" like ? "
				+ "order by num desc) q) "
				+ "where r between? and ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+rowNumber.getSearch().getSearch()+"%");
		st.setInt(2, rowNumber.getStartRow());
		st.setInt(3, rowNumber.getLastRow());
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			boardDTO = new BoardReplyDTO();
			boardDTO.setNum(rs.getInt("num"));
			boardDTO.setTitle(rs.getString("title"));
			boardDTO.setWriter(rs.getString("writer"));
			boardDTO.setContents(rs.getString("contents"));
			boardDTO.setReg_date(rs.getDate("reg_date"));
			boardDTO.setHit(rs.getInt("hit"));
			ar.add(boardDTO);
		}
		DBConnector.disConnect(rs, st, con);
		return ar;
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
		Connection con = DBConnector.getConnect();
		String sql = "select count(num) from qna "
				+ "where "+search.getKind()+ " like?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+search.getSearch() +"%");
		ResultSet rs = st.executeQuery();
		rs.next();
		int result = rs.getInt(1);
		DBConnector.disConnect(rs, st, con);
		return result;
	}

}
