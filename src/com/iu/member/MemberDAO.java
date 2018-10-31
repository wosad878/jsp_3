package com.iu.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.iu.util.DBConnector;

public class MemberDAO {

	public int join(MemberDTO memberDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "insert into member values(?,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		st.setString(3, memberDTO.getName());
		st.setString(4, memberDTO.getEmail());
		st.setString(5, memberDTO.getKind());
		st.setString(6, memberDTO.getClassmate());
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
	
	public MemberDTO selectOne(String id, String pw, String kind) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "select * from member where id=? and pw=? and kind=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		st.setString(2, pw);
		st.setString(3, kind);
		MemberDTO memberDTO = null;
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			memberDTO = new MemberDTO();
			memberDTO.setId(rs.getString("id"));
			memberDTO.setPw(rs.getString("pw"));
			memberDTO.setName(rs.getString("name"));
			memberDTO.setEmail(rs.getString("email"));
			memberDTO.setKind(rs.getString("kind"));
			memberDTO.setClassmate(rs.getString("classmate"));
		}
		DBConnector.disConnect(rs, st, con);
		return memberDTO;
	}
}
