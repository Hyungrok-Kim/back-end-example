package com.kh.jsp.common.exception;

public class MemberException extends Exception {
	// 서버에서 문제가 발생할 경우 
	// 이를 좀 더 처리하기 쉽도록 구현하는 예외 처리 클래스
	
	public MemberException () { }
	
	public MemberException (String msg) { 
		super(msg);
	}
}
