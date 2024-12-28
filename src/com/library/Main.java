package com.library;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.library.database.Database;
import com.library.impl.*;
import com.library.Book;
import com.library.impl.FindBookOperationImpl;

public class Main {
	public static void main(String args[]) throws SQLException {
		List<Book> bookcollection = new ArrayList<>();
		initializeBooks(bookcollection);
		
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println();
			System.out.println("Enter your need in our library(Please Enter a number)" + "\n" + "1.find Book " + "\n" + "2.Borrow Book" + "\n" + "3.Return Book" + "\n"+ "4.List of All Books with availability" + "\n" +"5.Exit");
			int input = scan.nextInt();
			scan.nextLine();
			if(input == 5) {
				System.out.println("Thank you for visiting library");
				break ;
			}
			else {
				try {
					selectOption(input, bookcollection,scan);
				}catch(NumberFormatException e) {
					System.out.println(e);
				}
			}
		}
		scan.close();
		
		
	}
	
	public static void initializeBooks(List<Book> bookcollection) throws SQLException {
		Connection conn = Database.getConnection();
		String sql = "SELECT * FROM library.bookrecords";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {
			bookcollection.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3)));
		}
	}
	
	public static void selectOption(int option , List<Book> bookcollection , Scanner scan) throws SQLException {
			BookOperation bookobj = new  FindBookOperationImpl();
			BookDAOImpl bookdaoimpl = new BookDAOImpl();
			Book book = new Book();
//		switch(option) {
//		
//			case 1 :
//				bookobj.borrowBook(bookdaoimpl , option);
//			break;
//				
//			case 2 :
//				bookdaoimpl.getAll(option);
//			break ;
//			
//			default :
//			System.out.println("Invalid option ");
//			break ;
//			
//	}
			
			String book_name ;
			switch(option) {
			case 1:
				System.out.println("Please Enter your search option to get your desired book \n1.BookName \n2.Author");
				//this nextLine gets called because nextInt always gets input and leaves a new line , So to avoid confusion nextLine gets the new line as input 
				int value = scan.nextInt();
				scan.nextLine();
				switch(value) {
					case 1 :
						System.out.println("Enter your bookname to check the availability of book in our library");
						book_name = scan.nextLine();
						bookobj.findBook(bookcollection , book_name , scan , option);
					break;
						
					case 2 :
						System.out.println("Enter your Author name to find the list of Books in our library ");
						String author = scan.nextLine();
						try {
							if(bookobj instanceof FindBookOperationImpl) {
								//Here findBookByAuthor a new method only present in FindBookOperationImpl and not present in BookOperationImpl.So java casting it to child class type to access the method .						
								((FindBookOperationImpl) bookobj).findBookByAuthor(bookcollection , author ,scan);
							}
						}catch(ClassCastException e) {
							System.out.println(e);
						}
					break ;
				}
			break ;//case 1 break of outer switch
				
			case 2 :
				
				//borrow option
				bookobj.borrowBook(bookdaoimpl , option);
			
		
			break ;
			case 3 :
				//return option
				bookobj.returnBook(bookdaoimpl , option);
				
			break;
			case 4 :
				//list of all books and their availability
				System.out.println();
				System.out.println("Please Find the Books and their Availability");
				bookdaoimpl.getAll(option);
			break ;
			default :
				System.out.println("Invalid option ");
			break ;
				
		}
	}
}
