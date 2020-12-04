package com.kh.ajax.model.vo;

public class Product {
	private int pno;  				// 상품 번호 
	private String pname; 			// 상품 명
	private String origin;			// 원산지 
	private String type;			// 상품 유형
	
	public Product() {
		super();
		
	}
	
	public Product(int pno, String pname, String origin, String type) {
		super();
		this.pno = pno;
		this.pname = pname;
		this.origin = origin;
		this.type = type;
	}
	
	
	
	@Override
	public String toString() {
		return "Product [pno=" + pno + ", pname=" + pname + ", origin=" + origin + ", type=" + type + "]";
	}

	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	

}
