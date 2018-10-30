package com.iu.file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iu.util.DBConnector;

public class FileDAO {
	
	public List<FileDTO> selectList(FileDTO fileDTO) throws Exception{
		List<FileDTO> ar = new ArrayList<>();
		Connection con = DBConnector.getConnect();
		String sql ="select * from upload where num=? and kind=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, fileDTO.getNum());
		st.setString(2, fileDTO.getKind());
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			fileDTO = new FileDTO();
			fileDTO.setFnum(rs.getInt("fnum"));
			fileDTO.setFname(rs.getString("fname"));
			fileDTO.setOname(rs.getString("oname"));
			fileDTO.setNum(rs.getInt("num"));
			fileDTO.setKind(rs.getString("kind"));
			ar.add(fileDTO);
		}
		DBConnector.disConnect(rs, st, con);
		return ar;
	}
	
	public int insert(FileDTO fileDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		
		String sql ="insert into upload values(file_seq.nextval, ?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, fileDTO.getFname());
		st.setString(2, fileDTO.getOname());
		st.setInt(3, fileDTO.getNum());
		st.setString(4, fileDTO.getKind());
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}

}








