package com.example.spring.user;

public class UserDTO {

	private int u_no;
	private String user_name;
	private String user_id;
	private String user_pw;
	
	public int getU_no() {
		return u_no;
	}
	public void setU_no(int u_no) {
		this.u_no = u_no;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	@Override
	public String toString() {
		return "User [u_no=" + u_no + ", user_name=" + user_name + ", user_id=" + user_id + ", user_pw=" + user_pw
				+ "]";
	}
	
}
