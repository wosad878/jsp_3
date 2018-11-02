package com.iu.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.iu.util.DBConnector;

public class MemberDAO {
	
	public boolean check(String id) throws Exception{
		boolean check = true;
		Connection con = DBConnector.getConnect();
		String sql = "select id from member where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		check = rs.next();
		DBConnector.disConnect(rs, st, con);
		return check;
	}
	
	public int delete(String id) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "delete member where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
	
	public int update(MemberDTO memberDTO) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "update member set pw=?, name=?, email=?, fname=?, oname=? "
				+ "where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getPw());
		st.setString(2, memberDTO.getName());
		st.setString(3, memberDTO.getEmail());
		st.setString(4, memberDTO.getFname());
		st.setString(5, memberDTO.getOname());
		st.setString(6, memberDTO.getId());
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}

	public int join(MemberDTO memberDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "insert into member values(?,?,?,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		st.setString(3, memberDTO.getName());
		st.setString(4, memberDTO.getEmail());
		st.setString(5, memberDTO.getKind());
		st.setString(6, memberDTO.getClassmate());
		st.setString(7, memberDTO.getFname());
		st.setString(8, memberDTO.getOname());
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
	
	public MemberDTO login(MemberDTO memberDTO) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "select * from member where id=? and pw=? and kind=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		st.setString(3, memberDTO.getKind());
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			memberDTO.setName(rs.getString("name"));
			memberDTO.setEmail(rs.getString("email"));
			memberDTO.setKind(rs.getString("kind"));
			memberDTO.setClassmate(rs.getString("classmate"));
			memberDTO.setFname(rs.getString("fname"));
			memberDTO.setOname(rs.getString("oname"));
		}else {
			memberDTO = null;
		}
		DBConnector.disConnect(rs, st, con);
		return memberDTO;
	}
}
