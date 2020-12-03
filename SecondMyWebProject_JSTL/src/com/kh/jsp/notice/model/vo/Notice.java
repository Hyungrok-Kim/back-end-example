package com.kh.jsp.notice.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Notice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 175L; 
	
	// 1. 필드 변수
	private int nno; 			// 공지사항 번호
	private String ntitle; 		// 공지사항 제목
	private String ncontent;	// 공지사항 내용
	private String nwriter;		// 작성자
	private int ncount;			// 조회수
	private Date ndate; 		// 작성일 (java.sql.Date 임포트)
	
	// 2. 생성자
	
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notice(int nno, String ntitle, String ncontent, String nwriter, int ncount, Date ndate) {
		super();
		this.nno = nno;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.nwriter = nwriter;
		this.ncount = ncount;
		this.ndate = ndate;
	}

	public Notice(String ntitle, String ncontent, String nwriter) {
		super();
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.nwriter = nwriter;
	}

	
	// 3. 기능제공 메소드
	
	@Override
	public String toString() {
		return "Notice [nno=" + nno + ", ntitle=" + ntitle + ", ncontent=" + ncontent + ", nwriter=" + nwriter
				+ ", ncount=" + ncount + ", ndate=" + ndate + "]";
	}

	public int getNno() {
		return nno;
	}

	public void setNno(int nno) {
		this.nno = nno;
	}

	public String getNtitle() {
		return ntitle;
	}

	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}

	public String getNcontent() {
		return ncontent;
	}

	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}

	public String getNwriter() {
		return nwriter;
	}

	public void setNwriter(String nwriter) {
		this.nwriter = nwriter;
	}

	public int getNcount() {
		return ncount;
	}

	public void setNcount(int ncount) {
		this.ncount = ncount;
	}

	public Date getNdate() {
		return ndate;
	}

	public void setNdate(Date ndate) {
		this.ndate = ndate;
	}
	
	
	
	
	
	
	
	
}
