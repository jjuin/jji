package com.example.spring.project;

public class ProjEmpDTO {

	private int emp_no;
	private String emp_name;
	private int emp_grade_cd;
	private String emp_grade_nm;
	
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
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
	
	@Override
	public String toString() {
		return "ProjEmpDTO [emp_no=" + emp_no + ", emp_name=" + emp_name + ", emp_grade_cd=" + emp_grade_cd
				+ ", emp_grade_nm=" + emp_grade_nm + "]";
	}

	
}
