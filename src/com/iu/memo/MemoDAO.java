package com.iu.memo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iu.page.RowNumber;
import com.iu.util.DBConnector;

public class MemoDAO {
	public int delete(int num) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "delete memo where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
	
	public int insert(MemoDTO memoDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="insert into memo values(memo_seq.nextval, ?, ?, sysdate)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memoDTO.getContents());
		st.setString(2, memoDTO.getWriter());
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
	
	public List<MemoDTO> selectList(RowNumber rowNumber) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql="select * from "
				+ "(select rownum R, M.* from "
				+ "(select * from memo order by num desc) M) "
				+ "where R between ? and ?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, rowNumber.getStartRow());
		st.setInt(2, rowNumber.getLastRow());
		
		ResultSet rs = st.executeQuery();
		List<MemoDTO> ar = new ArrayList<>();
		
		while(rs.next()) {
			MemoDTO memoDTO = new MemoDTO();
			memoDTO.setNum(rs.getInt("num"));
			memoDTO.setContents(rs.getString("contents"));
			memoDTO.setWriter(rs.getString("writer"));
			memoDTO.setReg_date(rs.getDate("reg_date"));
			ar.add(memoDTO);
		}
		DBConnector.disConnect(rs, st, con);
		return ar;
		
		
	}

}