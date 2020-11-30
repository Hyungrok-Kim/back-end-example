package com.kh.jdbc.member.model.service;

import static com.kh.jdbc.common.JDBCTemplate.close;
import static com.kh.jdbc.common.JDBCTemplate.commit;
import static com.kh.jdbc.common.JDBCTemplate.getConnection;
import static com.kh.jdbc.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kh.jdbc.member.model.dao.MemberDAO;
import com.kh.jdbc.member.model.vo.Member;

/*
 * 
 * service :
 * 		controller 인 서블릿에서
 * 		전달한 정보를 업무 수행 로직(bussiness logic)
 * 		에 따라 가공하여 실제 DB와 연결되는 DAO 객체에게 
 * 		전달하는 역할을 수행
 * 
 * */

public class MemberService {
	
	// 1. 찾아갈 데이터베이스 객체 선언
	private Connection con;
	
	private MemberDAO mDAO = new MemberDAO();
	
	public Member selectMember(Member m) {
		
		
		con = getConnection();
		
		Member result = mDAO.selectMember(con, m);
		
		close(con);
		
		return result;
	}

	public int insertMember(Member joinMember) {
		
		con = getConnection();
		int result = mDAO.insertMember(con, joinMember);
		
		if( result > 0 ) { // 회원 가입 성공 !
			commit(con);
		} else {	// 회원 가입 실패 !
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public int updateMember(Member m) {
		
		con = getConnection();
		
		int result = mDAO.updateMember(con, m);
		
		if(result > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public int deleteMember(String userId) {
		
		con = getConnection();
		
		int result = mDAO.deleteMember(con, userId);
		
		if(result > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
	
}
