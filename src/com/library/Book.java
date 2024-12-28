package com.library;

import java.sql.Date;

public class Book {
	private int BookId ;
	private String BookName ;
	private String Author ;
	private Date Return_date ;
	private String Availability ;
	
	
	public Book(int bookId, String bookName, String author) {
		this.BookId = bookId;
		this.BookName = bookName;
		this.Author = author;
	}
	
	public Date getReturn_date() {
		return Return_date;
	}

	public void setReturn_date(Date return_date) {
		Return_date = return_date;
	}

	public String getAvailability() {
		return Availability;
	}

	public void setAvailability(String availability) {
		Availability = availability;
	}

	public Book(int bookId, String bookName, Date date , String Availability) {
		this.BookId = bookId;
		this.BookName = bookName;
		this.Return_date = date;
		this.Availability = Availability;
	}

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public int getBookId() {
		return BookId;
	}

	public void setBookId(int bookId) {
		BookId = bookId;
	}

	public String getBookName() {
		return BookName;
	}

	public void setBookName(String bookName) {
		BookName = bookName;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	@Override
	public String toString() {
		return "Book [BookId=" + BookId + ", BookName=" + BookName + ", Author=" + Author + "]";
	}
	
}
