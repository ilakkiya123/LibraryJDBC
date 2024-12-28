package com.library.impl ;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.library.*;

public class FindBookOperationImpl extends BookOperationImpl{
	
	List<Book> filteredRecord = new ArrayList<>(); 
	Book b1 = new Book();

	@Override
	public Book findBook(List<Book> bookcollection, String title , Scanner scan , int option) {
		//bookcollection , Ponniyin Selvan-1 , scan , 3
	
		String trimmedTitle = title.trim();
		for(Book b : bookcollection) {
			if(b.getBookName().equalsIgnoreCase(trimmedTitle) || b.getBookName().toLowerCase().contains(trimmedTitle.toLowerCase())) {
				filteredRecord.add(b);
			}
		}
		if(filteredRecord.isEmpty()) {
			System.out.println("Book is not present");
			return null ;
		}
		else if(filteredRecord.size() == 1) {
			Book book = filteredRecord.get(0) ;
			System.out.println("Is this your required book ? " + filteredRecord.get(0) );
			String val = "Yes";
			String answer = scan.nextLine();
			if(val.equalsIgnoreCase(answer)) {
				System.out.println("Your book is available . " + filteredRecord.get(0) );
//				 if (option == 3) {
//					 	String bname = book.getBookName();
//						System.out.println(book.getBookName());
//						BorrowBook(bookcollection , bname);			
//		            }
				return book ;
			}
			else {
				System.out.println("Book is not present");
				return null ;
			}
		}
		else {
			//Display all books
			for(int i=0;i<filteredRecord.size() ;i++) {
				System.out.println((i+1) + "." + filteredRecord.get(i));
			}
			System.out.println("Is your book present in the List (Yes/No)? ");
			String val = "Yes";
			String answer = scan.nextLine();
			if(val.equalsIgnoreCase(answer)) {
				System.out.println("Enter the Book Index ");
				//Here user gives the number from the screen of list of books , So In screen index 0 will be shown as 1.ponniyin selvan 1 .So when printing book_index-1 is given.
				int book_index = scan.nextInt();
				if(book_index >= 1 && book_index <= filteredRecord.size()) {
					Book book = filteredRecord.get(book_index-1) ;
					System.out.println("Your Book is available - " + filteredRecord.get(book_index-1) );
//					System.out.println("Option : " + option);
//					if(option == 3) {
//						String bname = book.getBookName();
//						System.out.println(book.getBookName());
//						BorrowBook(bookcollection , bname);					
//						}
					return book ;
				}
			}
			else {
				System.out.println("Your Desired Book is not present in our Library ");
				return null ;
			}
		}
		
		return null ;
	}
	
	public void findBookByAuthor(List<Book> bookcollection, String author , Scanner scan) {
		String trimmedAuthor = author.trim() ;
		for(Book b : bookcollection) {
			if(b.getAuthor().equalsIgnoreCase(trimmedAuthor) || b.getAuthor().toLowerCase().contains(trimmedAuthor.toLowerCase())) {
				filteredRecord.add(b);
			}
		}
		if(filteredRecord.isEmpty()) {
			System.out.println("The Given Author Book is not present");
		}
		else if(filteredRecord.size() == 1) {
			System.out.println("Is this your required book ? " + filteredRecord.get(0) );
			String val = "Yes";
			String answer = scan.nextLine();
			if(val.equalsIgnoreCase(answer)) {
				System.out.println("Your book is available . " + filteredRecord.get(0) );
			}
			else {
				System.out.println("Book is not present");
			}
		}
		else {
			//Display all books
			for(int i=0;i<filteredRecord.size() ;i++) {
				System.out.println((i+1) + "." + filteredRecord.get(i));
			}
			
			System.out.println("Is your book present in the List ? ");
			String val = "Yes";
			String answer = scan.nextLine();
			if(val.equalsIgnoreCase(answer)) {
				System.out.println("Enter the Index ");
				int book_index = scan.nextInt();
				if(book_index > 1 && book_index < filteredRecord.size()) {
					System.out.println("Your Book is available - " + filteredRecord.get(book_index-1) );
				}
			}
			else {
				System.out.println("Your Desired Book is not present in our Library ");
			}
		}
	}
}
