package com.example.spring.project.util;

import java.util.Arrays;
import java.util.Date;

public class UploadDTO {
	
	private int file_no;
	private String file_name;
	private String[] files;   //fullname
	private Date file_regdate;
	public int getFile_no() {
		return file_no;
	}
	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}
	public Date getFile_regdate() {
		return file_regdate;
	}
	public void setFile_regdate(Date file_regdate) {
		this.file_regdate = file_regdate;
	}
	
	@Override
	public String toString() {
		return "UploadDTO [file_no=" + file_no + ", file_name=" + file_name + ", files=" + Arrays.toString(files)
				+ ", file_regdate=" + file_regdate + "]";
	}
	

}
