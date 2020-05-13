package com.example.spring.project.book;

import java.util.Date;

public class ProjBookDTO {
	
	private int book_no;
	private int cus_no;
	private String cus_name; //예약명
	private Date book_regdate; //등록일
	private String book_date; //예약일
//	private String book_regdate1;
//	private String book_regdate2;
	private String service_name;
	private int emp_no;
	private String emp_name; //사원이름 새롭게 추가
	private int work_time_cd;
	private String work_time; //없어도 무관?
	private char work_time_isshow = 'Y';
	
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public int getBook_no() {
		return book_no;
	}
	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}
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
	public Date getBook_regdate() {
		return book_regdate;
	}
	public void setBook_regdate(Date book_regdate) {
		this.book_regdate = book_regdate;
	}
	public String getBook_date() {
		return book_date;
	}
	public void setBook_date(String book_date) {
		this.book_date = book_date;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public int getWork_time_cd() {
		return work_time_cd;
	}
	public void setWork_time_cd(int work_time_cd) {
		this.work_time_cd = work_time_cd;
	}
	public String getWork_time() {
		return work_time;
	}
	public void setWork_time(String work_time) {
		this.work_time = work_time;
	}
	public char getWork_time_isshow() {
		return work_time_isshow;
	}
	public void setWork_time_isshow(char work_time_isshow) {
		this.work_time_isshow = work_time_isshow;
	}
	@Override
	public String toString() {
		return "ProjBookDTO [book_no=" + book_no + ", cus_no=" + cus_no + ", cus_name=" + cus_name + ", book_regdate="
				+ book_regdate + ", book_date=" + book_date + ", service_name=" + service_name + ", emp_no=" + emp_no
				+ ", emp_name=" + emp_name + ", work_time_cd=" + work_time_cd + ", work_time=" + work_time
				+ ", work_time_isshow=" + work_time_isshow + "]";
	}
	
	
}
