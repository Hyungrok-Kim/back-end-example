package com.kh.jsp.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
		// JDBC (Java DataBase Connectivity) 자바와 데이터베이스의 연결
		// JDBC 연결과 관련된 단일 메소드 작성
		//   : 여러 DAO들 간의 공통적인 JDBC 연결 구문을  
		//     하나의 클래스 안의 메소드들로 통합하는 형태의
		//     클래스로, 중복되는 소스 코드를 최대한 배재하며
		//     여러 개의 객체를 하나의 템플릿(양식)로 만듦으로써 공유하여
		//     사용할 수 있는 static 메소드의 형태로
		//     구현할 수 있다.
	
	
		/*
		 * Singleton 디자인 패턴
		 * 		여러 클래스가 공통되는 코드를 필요로 하거나 
		 * 		하나의 애플리케이션에서 단 하나의 클래스로 
		 * 		기능들을 관리하고자 할 때 사용하는 코딩 패턴
		 * 		00한 경우 이렇게 코드를 짜봐라! 하는 양식 ( like 고딕 양식 ) 
		 * 
		 * 		여러 메소드들을 static으로 관리하는 클래스 
		 * */
		public static Connection getConnection() {
			Connection con = null;
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				// getConnection( 오라클 서버 url , 사용자 계정, 비밀번호 );
				con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:xe",
						"JSP",
						"JSP");
				con.setAutoCommit(false); // 에러 여부와 관계없이 자동 커밋하는 것을 방지하는 목적
				
				
			} catch (ClassNotFoundException | SQLException e) { // 비트 연산자 ClassNotFound이거나 SQLException이거나
				
				e.printStackTrace();
			}
			
			return con;
		}

		/*
		 * JDBC 커넥션을 종료하기 위한 메소드 
		 * */
		
		// 오버로딩 적용하기
		public static void close(Statement stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		public static void close(ResultSet rset) {
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		public static void close(Connection con) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		public static void commit(Connection con) {
			// 변경사항 저장 메소드
			
			try {
				con.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void rollback(Connection con) {
			// 변경사항 취소 메소드 
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
