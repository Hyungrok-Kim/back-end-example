package com.kh.jsp.thumb.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Attachment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1200L;
	
	private int fno;
	private int bno;
	private String originname;
	private String changename;
	private String filepath;
	private int flevel;
	private Date uploaddate;
	private String status;
	
	
	
	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Attachment(int fno, int bno, String originname, String changename, String filepath, int flevel,
			Date uploaddate, String status) {
		super();
		this.fno = fno;
		this.bno = bno;
		this.originname = originname;
		this.changename = changename;
		this.filepath = filepath;
		this.flevel = flevel;
		this.uploaddate = uploaddate;
		this.status = status;
	}
	
	

	public Attachment(String originname, String changename, String filepath) {
		super();
		this.originname = originname;
		this.changename = changename;
		this.filepath = filepath;
	}



	@Override
	public String toString() {
		return "Attachment [fno=" + fno + ", bno=" + bno + ", originname=" + originname + ", changename=" + changename
				+ ", filepath=" + filepath + ", flevel=" + flevel + ", uploaddate=" + uploaddate + ", status=" + status
				+ "]";
	}



	public int getFno() {
		return fno;
	}



	public void setFno(int fno) {
		this.fno = fno;
	}



	public int getBno() {
		return bno;
	}



	public void setBno(int bno) {
		this.bno = bno;
	}



	public String getOriginname() {
		return originname;
	}



	public void setOriginname(String originname) {
		this.originname = originname;
	}



	public String getChangename() {
		return changename;
	}



	public void setChangename(String changename) {
		this.changename = changename;
	}



	public String getFilepath() {
		return filepath;
	}



	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}



	public int getFlevel() {
		return flevel;
	}



	public void setFlevel(int flevel) {
		this.flevel = flevel;
	}



	public Date getUploaddate() {
		return uploaddate;
	}



	public void setUploaddate(Date uploaddate) {
		this.uploaddate = uploaddate;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
}
