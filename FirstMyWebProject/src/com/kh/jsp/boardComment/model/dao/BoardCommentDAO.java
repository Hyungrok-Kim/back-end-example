package com.kh.jsp.boardComment.model.dao;

import static com.kh.jsp.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.jsp.boardComment.model.vo.BoardComment;

public class BoardCommentDAO {

		private Properties prop;
		
		public BoardCommentDAO() {
			prop = new Properties();
			
			String filePath = BoardCommentDAO.class
											 .getResource("/config/comment-sql.properties")
											 .getPath();
			
			try {
				prop.load(new FileReader(filePath));
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		
	
	public int insertComment(Connection con, BoardComment comment) {

		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertComment");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, comment.getBno());
			pstmt.setString(2, comment.getCcontent());
			pstmt.setString(3, comment.getCwriter());

			// 첫 댓글은 참조하는 댓글이 없다.
			// 따라서 refcno가 0으로 온다. 
			
			if(comment.getRefcno() > 0) {
				pstmt.setInt(4, comment.getRefcno());
			}else {
				pstmt.setNull(4, java.sql.Types.NULL);
			}
			
			pstmt.setInt(5, comment.getClevel());
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}



	public ArrayList<BoardComment> selectList(Connection con, int bno) {
		ArrayList<BoardComment> clist = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null; // select니까 ResultSet이 필요하다.
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				BoardComment bco = new BoardComment();
				
				bco.setCno(rset.getInt(1));
				bco.setBno(rset.getInt(2));
				bco.setCcontent(rset.getString(3));
				bco.setCwriter(rset.getString("USERNAME"));
				bco.setUserId(rset.getString("CWRITER"));
				bco.setCdate(rset.getDate("cdate"));
				bco.setRefcno(rset.getInt("ref_cno"));//DB컬럼이름대로
				bco.setClevel(rset.getInt("clevel"));
				
				clist.add(bco);
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		return clist;
	}



	public int updateComment(Connection con, BoardComment bco) {

		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateComment");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bco.getCcontent());
			pstmt.setInt(2, bco.getCno());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(pstmt);
			
		}
		
		
		
		return result;
	}



	public int deleteComment(Connection con, int cno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteComment");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, cno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { 
			
			close(pstmt);
			
		}
		
		return result;
	}

	
	
}
