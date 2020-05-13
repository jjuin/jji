package com.example.spring.test;

public class BookDTO {
	
	private long book_no; //번호
	private String title; //제목
	private String creator; //작가
	private String publisher; //출판사
	private int publishedYear; //발행년도
	
	public long getBook_no() {
		return book_no;
	}
	public void setBook_no(long book_no) {
		this.book_no = book_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPublishedYear() {
		return publishedYear;
	}
	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}
	@Override
	public String toString() {
		return "BookDTO [book_no=" + book_no + ", title=" + title + ", creator=" + creator + ", publisher=" + publisher
				+ ", publishedYear=" + publishedYear + "]";
	}
	
	

}
