package com.kh.jsp.thumb.model.service;

import static com.kh.jsp.common.JDBCTemplate.close;
import static com.kh.jsp.common.JDBCTemplate.commit;
import static com.kh.jsp.common.JDBCTemplate.getConnection;
import static com.kh.jsp.common.JDBCTemplate.rollback;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.jsp.thumb.model.dao.ThumbnailDAO;
import com.kh.jsp.thumb.model.vo.Attachment;
import com.kh.jsp.thumb.model.vo.Thumbnail;

public class ThumbnailService {

	private Connection con;
	private ThumbnailDAO tDAO = new ThumbnailDAO();
	
	
	public ArrayList<Thumbnail> selectList() {
		con = getConnection();
		
		ArrayList<Thumbnail> list = tDAO.selectList(con);
		
		close(con);
		
		
		return list;
	}


	public int insertThumbnail(Thumbnail t, ArrayList<Attachment> list) {
		con = getConnection();
		
		int result = 0;
		
		// 저장해야 될 것 이 두 가지 ( 사진 게시글 , 첨부파일 여러 개) 
		// 1. 사진 게시글 저장
		int result1 = tDAO.insertThumbnail(con, t);
		
		if(result1 > 0) {
			int bno = tDAO.getCurrentBno(con);
			System.out.println(bno);
			
			for(int i = 0; i < list.size(); i++) {
				list.get(i).setBno(bno);
			}
		}
		
		// 2. 첨부파일 여러 개 저장
		int result2 = 0;
		for(int i = 0; i < list.size(); i++) {
			// 첫번째 이미지는 대표 이미지 (flevel = 0)
			// 나머지는 서브이미지 (flevel = 1)로 설정한다. 
			
			list.get(i).setFlevel(i == 0 ? 0 : 1);
			
			result2 = tDAO.insertAttachment(con,  list.get(i));
			
			if( result2 == 0) {
				break; // 잘못 실행된 행이 있다면 반복(insert) 취소! 
			} 
			
			
		}
		
		if(result1 > 0 && result2 > 0) {
			commit(con);
			result = 1;
		}else {
			rollback(con);
		}
		
		close(con);
		
		
		
		
		return result;
	}


	public HashMap<String, Object> selectOne(int bno) {
			con = getConnection();
			
			HashMap<String, Object> hmap = tDAO.selectOne(con, bno);
			
			close(con);
			
		return hmap;
	}


	public HashMap<String, Object> getUpdateView(int bno) {
		con = getConnection();
		
		HashMap<String, Object> hmap = tDAO.selectOne(con, bno);
		
		close(con);
		
		return hmap;
	}


	public int updateThumbnail(Thumbnail t, ArrayList<Attachment> list) {
		con = getConnection();
		
		int totalResult = 0;
		
		int result1 = tDAO.updateThumbnail(con, t);
		
		if(result1 > 0) {
			int result2 = 0;
			
			for(Attachment a : list) {
				result2 = tDAO.updateAttachment(con, a);
				
				if(result2 == 0){
					break;
				}
				
				
			}
			
			if(result2 > 0) {
				commit(con);
				totalResult = 1;
			} else rollback(con);
		}
		
		close(con);
		
		return totalResult;
	}


	public int deleteThumbnail(int bno, String savePath) {
		con = getConnection();
		
		HashMap<String, Object> hmap = tDAO.selectOne(con, bno);
		
		int result = tDAO.deleteThumbnail(con, bno);
		
		if(result > 0) {
			// 게시글 삭제가 완료되었다면, 첨부파일도 삭제한다.
			
			ArrayList<Attachment> list = (ArrayList<Attachment>)hmap.get("attachment");
			
			for(Attachment a : list) {
				File f = new File(savePath + a.getChangename());
				
				f.delete();
			}
			
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

}
