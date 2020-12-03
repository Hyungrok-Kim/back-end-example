package com.kh.jsp.thumb.model.dao;

import static com.kh.jsp.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.kh.jsp.thumb.model.vo.Attachment;
import com.kh.jsp.thumb.model.vo.Thumbnail;


public class ThumbnailDAO {

	private Properties prop = null;
	
	public ThumbnailDAO() {
		prop = new Properties();
		
		String filePath = ThumbnailDAO.class
									  .getResource("/config/thumbnail-sql.properties")
									  .getPath();
		
		try {
			
			prop.load(new FileReader(filePath));
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	public ArrayList<Thumbnail> selectList(Connection con) {
		ArrayList<Thumbnail> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Thumbnail tn = new Thumbnail();
				
				tn.setBno(rset.getInt("bno"));
				tn.setBtitle(rset.getString("btitle"));
				tn.setBcontent(rset.getString("bcontent"));
				tn.setBwriter(rset.getString("username"));
				tn.setBcount(rset.getInt("bcount"));
				tn.setBdate(rset.getDate("bdate"));
				tn.setBoardfile(rset.getString("changename"));
				
				list.add(tn);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}


	public int insertThumbnail(Connection con, Thumbnail t) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertThumbnail");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, t.getBtitle());
			pstmt.setString(2, t.getBcontent());
			pstmt.setString(3, t.getBwriter());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			
			close(pstmt);
		}
		
		return result;
	}


	public int getCurrentBno(Connection con) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null; // select로 가져와야하는 것들은 ResultSet
		
		String sql = prop.getProperty("currentBno");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return result;
	}


	public int insertAttachment(Connection con, Attachment attachment) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, attachment.getBno());
			pstmt.setString(2, attachment.getOriginname());
			pstmt.setString(3, attachment.getChangename());
			pstmt.setString(4, attachment.getFilepath());
			pstmt.setInt(5, attachment.getFlevel());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}


	public HashMap<String, Object> selectOne(Connection con, int bno) {
		HashMap<String, Object> hmap = new HashMap<>();
		ArrayList<Attachment> list = new ArrayList<>();
		Thumbnail t = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				t = new Thumbnail();
				
				t.setBno(bno);
				t.setBtitle(rset.getString("btitle"));
				t.setBcontent(rset.getString("bcontent"));
				t.setBwriter(rset.getString("username"));
				t.setUserId(rset.getString("bwriter"));
				t.setBcount(rset.getInt("bcount"));
				t.setBdate(rset.getDate("bdate"));
				
				// -----여기까지가 게시글 내용 
				Attachment at = new Attachment();
				
				at.setFno(rset.getInt("fno"));
				at.setBno(bno);
				at.setOriginname(rset.getString("originname"));
				at.setChangename(rset.getString("changename"));
				at.setFilepath(rset.getString("filepath"));
				at.setUploaddate(rset.getDate("uploaddate"));
				at.setFlevel(rset.getInt("flevel"));
				
				list.add(at);
				
			}
			
			hmap.put("thumbnail", t);
			hmap.put("attachment", list);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		
		return hmap;
	}


	public int updateThumbnail(Connection con, Thumbnail t) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateThumbnail");
		
		try {
			pstmt = con.prepareStatement(sql);
						
			pstmt.setString(1, t.getBtitle());
			pstmt.setString(2, t.getBcontent());
			pstmt.setInt(3, t.getBno());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}


	public int updateAttachment(Connection con, Attachment a) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateAttachment");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, a.getOriginname());
			pstmt.setString(2, a.getChangename());
			pstmt.setInt(3, a.getFno());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		 
		return result;
	}


	public int deleteThumbnail(Connection con, int bno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteThumbnail");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

}
