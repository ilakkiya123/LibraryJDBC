package com.library.impl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.library.Book;
import com.library.BookDAO;
import com.library.database.Database;

public class BookDAOImpl implements BookDAO{

	@Override
	public List<Book> getAll(int option) throws SQLException {
		
		List<Book> books = new ArrayList<>();
		Connection conn = Database.getConnection();
		//For both option 2
		//option 2 return 
		//option 3 borrow - user checking the list of all books to borrow , so only showing name , id and author
		if(option == 2 || option == 3) {
			
			String sql = "SELECT * FROM library.bookrecords";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			// Print table header
		    System.out.printf("%-10s %-50s %-20s%n", "BookId", "BookName", "Author");
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String author = rs.getString(3);
				books.add(new Book(id,name,author));
				System.out.printf("%-10d %-50s%-20s%n",id,name,author);
				
			}
		}
		//option 4 list all books and their availability .Borrow table has availability col so redirecting the availability call here.
			else if(option == 4) {
				String sql = "SELECT * FROM library.bookavailability";
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				
				// Print table header
			    System.out.printf("%-10s %-50s %-30s %-20s%n", "BookId","BookName", "Return_Date", "Availability");
				
				while(rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					Date date = rs.getDate(3);
					String availability = rs.getString(4);
					books.add(new Book(id,name,date,availability));
					System.out.printf("%-10s %-50s %-30s %-20s%n",id,name,date,availability);
			}
		}
		return books;
		
	}

	@Override
	public Book getId(int id , int option) throws SQLException {
//		System.out.println("In getId " + id);
		Book book = null ;
		Connection conn = Database.getConnection();
		
			if(option == 2) {
				String sql = "SELECT * FROM library.bookrecords where BookId = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery(); 
//				//header
//				System.out.printf("%-10s %-30s %-20s%n", "BookId", "BookName", "Author");
				while(rs.next()) {
					int bookid = rs.getInt(1);
					String name = rs.getString(2);
					String author = rs.getString(3);
					
					book = new Book(bookid , name , author);
					
				}
			}
			if(option == 3) {
				String sql = "SELECT * FROM library.bookavailability where BookId = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery(); 
//				//header
//				System.out.printf("%-10s %-30s %-20s%n", "BookId", "BookName", "Author");
				while(rs.next()) {
					int bookid = rs.getInt(1);
					String bookname = rs.getString(2);
					Date return_date = rs.getDate(3);
					String availability = rs.getString(4);
					
					book = new Book(bookid , bookname , return_date , availability );
					
				}
			}
		
		return book;
	}
	
	public Book getName(String name) throws SQLException {

		Book book = null ;
//		System.out.println("Name : " + name);
		Connection conn = Database.getConnection();
		String sql = "SELECT * FROM library.bookavailability where BookName = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery(); 

		while(rs.next()) {
			int bookid = rs.getInt(1);
			String book_name = rs.getString(2);
			Date return_date = rs.getDate(3);
			String availability = rs.getString(4);
			
//			System.out.println(bookid + " : " + " : " + book_name + " : " + return_date + " : " + availability);
			
			book = new Book(bookid , book_name , return_date , availability);
			System.out.println("Inside while : " + book);
		}
		System.out.println(book);
		return book;
	}

	@Override
	public int insert(Book book) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(int id , int option) throws SQLException {
		List<Book> books = new ArrayList<>();
		Connection conn = Database.getConnection();
		int result = 0 ;
		if(option == 2) {
			String sql = "UPDATE library.bookavailability set Availability = 'borrowed', Return_date = date_add((curdate()), Interval ? day) where BookId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			//return date is default set as 10 , ie 10 days after they should return the book
			ps.setInt(1, 10);
			ps.setInt(2, id);
			result = ps.executeUpdate();
		}
		else if(option == 3) {
			String sql = "UPDATE library.bookavailability set Availability = 'IN', Return_date = null where BookId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			result = ps.executeUpdate();
		}
		
		return result;
	}

	public boolean isAvailable(int id) throws SQLException{
		
		List<Book> books = new ArrayList<>();
		Connection conn = Database.getConnection();
		String available = null ;
		String sql = "SELECT Availability FROM library.bookavailability WHERE BookId = ?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			return rs.getString("Availability").equalsIgnoreCase("IN");
		}
		return false;
		
		
	}
	@Override
	public int delete(Book book) {
		// TODO Auto-generated method stub
		return 0;
	}

}
