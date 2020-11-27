package com.kh.jsp.member.model.vo;

import java.io.Serializable;
import java.sql.Date;

// VO ( Value Object : Domain, DTO, Entity )
// 데이터 전달용 그릇
public class Member implements Serializable {

	/**
	 * 다른 위치(패키지)에 정의된 Member 클래스가 있을 경우
	 * 이를 헷갈리지 않게 하기 위한 전용 ID 번호 설정
	 * ex) ISBN, RRN(주민번호)
	 */
	private static final long serialVersionUID = 1004L;
	
	/*
	 * USERID, PASSWORD, USERNAME, GENDER, AGE, 
	 * EMAIL, PHONE, ADDRESS, HOBBY, ENROLLDATE
	 */
	// 1. 필드 변수 선언 ( Alt + Shift + 'A' )
	private String userId;   // 사용자 아이디
	private String userPwd;  // 사용자 비밀번호
	private String userName; // 사용자 이름
	private String gender;   // 성별
	private int age;         // 나이
	private String email;    // 이메일
	private String phone;    // 연락처
	private String address;  // 주소
	private String hobby;    // 취미
	private Date enrollDate; // 가입일 (java.sql.Date 로 임포트)
	
	// 2. 생성자
	// Alt + Shift + 'S' > Generate Constructors from Superclass
	public Member() { }

	// Alt + Shift + 'S' > Generate Constructors using fields
	// 회원의 모든 정보를 가져오기 위한 생성자
	public Member(String userId, String userPwd, String userName, String gender, int age, String email, String phone,
			String address, String hobby, Date enrollDate) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}

	// 회원 로그인 시 아이디와 비밀번호를 받아 올 생성자
	public Member(String userId, String userPwd) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
	}

	// 회원 가입용 생성자
	public Member(String userId, String userPwd, String userName, String gender, int age, String email, String phone,
			String address, String hobby) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
	}

	
	// 3. 기능 제공 메소드( getter & setter)
	
	// toString() 재정의 
	// Alt + Shift + 'S'
	
	
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", gender=" + gender
				+ ", age=" + age + ", email=" + email + ", phone=" + phone + ", address=" + address + ", hobby=" + hobby
				+ ", enrollDate=" + enrollDate + "]";
	}
	
	// Alt + Shift + 'S' --> Generate getters and setters
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	
	
	
	
	
}
