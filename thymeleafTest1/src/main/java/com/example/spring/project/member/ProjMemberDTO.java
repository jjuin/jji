package com.example.spring.project.member;

import java.util.Date;

public class ProjMemberDTO {

	private int cus_no;
	private String cus_name;
	private String cus_id;
	private String cus_pw;
	private String cus_phone;
	private String cus_phone1;
	private String cus_phone2;
	private String cus_phone3;
	private String cus_gender;
	private String cus_email;
	private String cus_email1;
	private String cus_email2;
	private String cus_emailckeck;
	private Date cus_date;
	private Date cus_updatedate;   //수정일
	private Date cus_out;		//탈퇴일
	private int cus_grade_cd; //회원등급코드
	
	//private int count = 0; //회원 가입되어있는지 
	
	
	public int getCus_no() {
		return cus_no;
	}
	public void setCus_no(int cus_no) {
		this.cus_no = cus_no;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getCus_id() {
		return cus_id;
	}
	public void setCus_id(String cus_id) {
		this.cus_id = cus_id;
	}
	public String getCus_pw() {
		return cus_pw;
	}
	public void setCus_pw(String cus_pw) {
		this.cus_pw = cus_pw;
	}
	public String getCus_phone() {
		return cus_phone;
	}
	public void setCus_phone(String cus_phone) {
		this.cus_phone = cus_phone;
	}
	public String getCus_phone1() {
		return cus_phone1;
	}
	public void setCus_phone1(String cus_phone1) {
		this.cus_phone1 = cus_phone1;
	}
	public String getCus_phone2() {
		return cus_phone2;
	}
	public void setCus_phone2(String cus_phone2) {
		this.cus_phone2 = cus_phone2;
	}
	public String getCus_phone3() {
		return cus_phone3;
	}
	public void setCus_phone3(String cus_phone3) {
		this.cus_phone3 = cus_phone3;
	}
	public String getCus_gender() {
		return cus_gender;
	}
	public void setCus_gender(String cus_gender) {
		this.cus_gender = cus_gender;
	}
	public String getCus_email() {
		return cus_email;
	}
	public void setCus_email(String cus_email) {
		this.cus_email = cus_email;
	}
	public String getCus_email1() {
		return cus_email1;
	}
	public void setCus_email1(String cus_email1) {
		this.cus_email1 = cus_email1;
	}
	public String getCus_email2() {
		return cus_email2;
	}
	public void setCus_email2(String cus_email2) {
		this.cus_email2 = cus_email2;
	}
	public String getCus_emailckeck() {
		return cus_emailckeck;
	}
	public void setCus_emailckeck(String cus_emailckeck) {
		this.cus_emailckeck = cus_emailckeck;
	}
	public Date getCus_date() {
		return cus_date;
	}
	public void setCus_date(Date cus_date) {
		this.cus_date = cus_date;
	}
	public Date getCus_updatedate() {
		return cus_updatedate;
	}
	public void setCus_updatedate(Date cus_updatedate) {
		this.cus_updatedate = cus_updatedate;
	}
	public Date getCus_out() {
		return cus_out;
	}
	public void setCus_out(Date cus_out) {
		this.cus_out = cus_out;
	}
	public int getCus_grade_cd() {
		return cus_grade_cd;
	}
	public void setCus_grade_cd(int cus_grade_cd) {
		this.cus_grade_cd = cus_grade_cd;
	}
	
	
	@Override
	public String toString() {
		return "ProjMemberDTO [cus_no=" + cus_no + ", cus_name=" + cus_name + ", cus_id=" + cus_id + ", cus_pw="
				+ cus_pw + ", cus_phone=" + cus_phone + ", cus_phone1=" + cus_phone1 + ", cus_phone2=" + cus_phone2
				+ ", cus_phone3=" + cus_phone3 + ", cus_gender=" + cus_gender + ", cus_email=" + cus_email
				+ ", cus_email1=" + cus_email1 + ", cus_email2=" + cus_email2 + ", cus_emailckeck=" + cus_emailckeck
				+ ", cus_date=" + cus_date + ", cus_updatedate=" + cus_updatedate + ", cus_out=" + cus_out
				+ ", cus_grade_cd=" + cus_grade_cd + "]";
	}
	
	
	
}
