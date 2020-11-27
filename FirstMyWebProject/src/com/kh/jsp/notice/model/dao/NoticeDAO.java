package com.kh.jsp.notice.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.jsp.common.JDBCTemplate.*;

import com.kh.jsp.common.exception.NoticeException;
import com.kh.jsp.member.model.dao.DeptDto;
import com.kh.jsp.notice.model.vo.Notice;

public class NoticeDAO {

	private Properties prop;
	
	public NoticeDAO() {
		prop = new Properties();
		
		String filePath = NoticeDAO.class
									.getResource("/config/notice-sql.properties")
									.getPath();
	
		
		try {
			prop.load(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	
	}
	
	
	
	public ArrayList<Notice> selectList(Connection con) throws NoticeException {
		ArrayList<Notice> list = new ArrayList<>(); // 공지사항 목록 담을 공간 
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Notice n = new Notice();
				
				n.setNno(rset.getInt(1));
				n.setNtitle(rset.getString(2));
				n.setNcontent(rset.getString(3));
				n.setNwriter(rset.getString(4));
				n.setNcount(rset.getInt(5));
				n.setNdate(rset.getDate(6));
				
				list.add(n);
				
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new NoticeException("[DAO] : " + e.getMessage());
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}
	
	
	

	public int insertNotice(Connection con, Notice n) throws NoticeException {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNotice");
		
		try {
			pstmt = con.prepareStatement(sql);
		
			pstmt.setString(1, n.getNtitle());
			pstmt.setString(2, n.getNcontent());
			pstmt.setString(3, n.getNwriter());
			pstmt.setDate(4, n.getNdate());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoticeException("[DAO] :" + e.getMessage());
			
			
		}	finally {
			close(pstmt);
			
		}
		
		
		return result;
	}
	
	public Notice selectOne(Connection con, int nno) throws NoticeException {
		Notice result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectOne");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, nno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = new Notice();
				
				result.setNno(nno);
				result.setNtitle(rset.getString("ntitle"));
				result.setNcontent(rset.getString("ncontent"));
				result.setNwriter(rset.getString("nwriter"));
				result.setNcount(rset.getInt("ncount"));
				result.setNdate(rset.getDate("ndate"));
				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new NoticeException("[DAO] : " + e.getMessage());
			
		} finally {
			close(rset);
			close(pstmt);
			
		}
		
		return result;
	}



	public int updateReadCount(Connection con, int nno) throws NoticeException {
		int result = 0;
		PreparedStatement pstmt = null; 
		String sql = prop.getProperty("updateReadCount");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, nno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			throw new NoticeException("[DAO] : " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		
		
		return result;
	}



	public int updateNotice(Connection con, Notice n) throws NoticeException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateNotice");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, n.getNtitle());
			pstmt.setString(2, n.getNcontent());
			pstmt.setInt(	3, n.getNno());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new NoticeException("[DAO] : " + e.getMessage());
			
		} finally {
			
			close(pstmt);
			
		}
		
		
		
		return result;
	}



	public int deleteNotice(Connection con, int nno) throws NoticeException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteNotice");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, nno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new NoticeException("[DAO] : " + e.getMessage());
			
		} finally {
			close(pstmt);
		}
		return result;
	}



	public ArrayList<Notice> searchList(Connection con, String condition, String keyword) throws NoticeException {
		
		ArrayList<Notice> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = null;
		
		switch(condition) {
		case "writer" : // 작성자로 검색한 경우
			sql = prop.getProperty("searchWriter");
			break;
		case "title" : // 제목으로 검색한 경우 
			sql = prop.getProperty("searchTitle");
			break;
		case "content" : // 내용으로 검색한 경우
			sql = prop.getProperty("searchContent");
			break;
		}
		
		try {
			pstmt = con.prepareStatement(sql);
		    
			pstmt.setString(1, keyword);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice n = new Notice();
				
				n.setNno(rset.getInt(1));
				n.setNtitle(rset.getString(2));
				n.setNcontent(rset.getString(3));
				n.setNwriter(rset.getString(4));
				n.setNcount(rset.getInt(5));
				n.setNdate(rset.getDate(6));
				
				list.add(n);
			}
			
			
			
		} catch (SQLException e) {
			// 
			e.printStackTrace();
			throw new NoticeException("[DAO] : " + e.getMessage());
		} finally {
			
			close(rset);
			close(pstmt);
		
		}
		
		return list;
	}



	public ArrayList<Notice> searchAll(Connection con, String keyword) throws NoticeException {

		ArrayList<Notice> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchAll");
		
		try {
			pstmt = con.prepareStatement(sql);
		    
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			pstmt.setString(3, keyword);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice n = new Notice();
				
				n.setNno(rset.getInt(1));
				n.setNtitle(rset.getString(2));
				n.setNcontent(rset.getString(3));
				n.setNwriter(rset.getString(4));
				n.setNcount(rset.getInt(5));
				n.setNdate(rset.getDate(6));
				
				list.add(n);
			}
			
			
			
		} catch (SQLException e) {
			// 
			e.printStackTrace();
			throw new NoticeException("[DAO] : " + e.getMessage());
			
		} finally {
			
			close(rset);
			close(pstmt);
		
		}
		
		return list;
		
	}
	
}
