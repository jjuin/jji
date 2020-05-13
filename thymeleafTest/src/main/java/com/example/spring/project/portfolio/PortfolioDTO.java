package com.example.spring.project.portfolio;

import java.util.Arrays;
import java.util.Date;

public class PortfolioDTO {
	
	private int portfolio_no;
	private int portfolio_good_cnt;
	private int emp_no;
	private Date portfolio_regdate;
	private char portfolio_isshow;
	
	private int file_no;
	private String files;
	//private Date file_regdate;
	
	private String emp_name;
	private int emp_grade_cd;
	private String emp_grade_nm;
	
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public int getEmp_grade_cd() {
		return emp_grade_cd;
	}
	public void setEmp_grade_cd(int emp_grade_cd) {
		this.emp_grade_cd = emp_grade_cd;
	}
	public String getEmp_grade_nm() {
		return emp_grade_nm;
	}
	public void setEmp_grade_nm(String emp_grade_nm) {
		this.emp_grade_nm = emp_grade_nm;
	}
	public int getPortfolio_no() {
		return portfolio_no;
	}
	public void setPortfolio_no(int portfolio_no) {
		this.portfolio_no = portfolio_no;
	}
	public int getPortfolio_good_cnt() {
		return portfolio_good_cnt;
	}
	public void setPortfolio_good_cnt(int portfolio_good_cnt) {
		this.portfolio_good_cnt = portfolio_good_cnt;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public Date getPortfolio_regdate() {
		return portfolio_regdate;
	}
	public void setPortfolio_regdate(Date portfolio_regdate) {
		this.portfolio_regdate = portfolio_regdate;
	}
	public char getPortfolio_isshow() {
		return portfolio_isshow;
	}
	public void setPortfolio_isshow(char portfolio_isshow) {
		this.portfolio_isshow = portfolio_isshow;
	}
	public int getFile_no() {
		return file_no;
	}
	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}
	
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	@Override
	public String toString() {
		return "PortfolioDTO [portfolio_no=" + portfolio_no + ", portfolio_good_cnt=" + portfolio_good_cnt + ", emp_no="
				+ emp_no + ", portfolio_regdate=" + portfolio_regdate + ", portfolio_isshow=" + portfolio_isshow
				+ ", file_no=" + file_no + ", files=" + files + ", emp_name=" + emp_name + ", emp_grade_cd="
				+ emp_grade_cd + ", emp_grade_nm=" + emp_grade_nm + "]";
	}
	
	
}
