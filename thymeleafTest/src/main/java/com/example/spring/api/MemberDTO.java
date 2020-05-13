package com.example.spring.api;

import java.util.Date;

public class MemberDTO {
	
	int ori_no;
	String cus_id;
	String cus_pw;
	String cus_name;
	Date cus_hiredate;
	String cus_isshow;
	public int getOri_no() {
		return ori_no;
	}
	public void setOri_no(int ori_no) {
		this.ori_no = ori_no;
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
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public Date getCus_hiredate() {
		return cus_hiredate;
	}
	public void setCus_hiredate(Date cus_hiredate) {
		this.cus_hiredate = cus_hiredate;
	}
	public String getCus_isshow() {
		return cus_isshow;
	}
	public void setCus_isshow(String cus_isshow) {
		this.cus_isshow = cus_isshow;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [ori_no=" + ori_no + ", cus_id=" + cus_id + ", cus_pw=" + cus_pw + ", cus_name=" + cus_name
				+ ", cus_isshow=" + cus_isshow + "]";
	}
	
	

}
