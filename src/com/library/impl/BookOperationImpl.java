package com.library.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import com.library.*;
import com.library.database.*;
import com.library.impl.*;
import com.library.Book.*;

public abstract class BookOperationImpl implements BookOperation{

	@Override
	public void borrowBook(BookDAOImpl bookdaoimpl , int option) throws SQLException {
		Scanner scan;
		List<Book> books = bookdaoimpl.getAll(option);
		System.out.println();
		System.out.println("Is your required book present in the list ? (Yes/No)");
		scan = new Scanner(System.in);
		String reply = scan.nextLine();
		if(reply.equalsIgnoreCase("Yes")) {
			System.out.println("Enter the book Id which you like to borrow ");
			scan = new Scanner(System.in);
			int id = scan.nextInt();
			scan.nextLine();
			if(bookdaoimpl.isAvailable(id)) {
				int result = bookdaoimpl.update(id,option);
				if(result != 0) {
					Book book = bookdaoimpl.getId(id,option);
					System.out.println("You have Borrowed your Book successfully " + book);
				}
			}else {
				System.out.println("Oops your required book is already borrowed ");
			}
		}
		else {
			System.out.println("Sorry Your Searched Book is not available in our library");
		}
		
	}
	
	public void returnBook(BookDAOImpl bookdaoimpl , int option) throws SQLException {
		//getAll - to check with use the list of books in which the book he came to return
		//getId - to get the book which user said and to get the return date to check penalty due 
		//Update - to change availability to IN and returndate to null 
		
		Scanner scan;
		List<Book> books = bookdaoimpl.getAll(option);
		System.out.println();
		System.out.println("Enter the Book Id you want to return back to the library ");
		scan = new Scanner(System.in);
		int id = scan.nextInt();
		scan.nextLine();
		Book book = bookdaoimpl.getId(id,option);
		System.out.println("return id ..." + book);
		
		long daysCount = 0;
		//to get the current date
		LocalDate currentDate = LocalDate.now();
		
		Date ret_date = book.getReturn_date();
		if(ret_date.toLocalDate().isBefore(currentDate)) {
			daysCount = ChronoUnit.DAYS.between(ret_date.toLocalDate() ,currentDate);
			double penalty_amt = penalty(daysCount);
			
			System.out.println("Your due by: " + daysCount + " .Pls pay the penalty amount: " + penalty_amt);
		}
		else if(ret_date.toLocalDate().isEqual(currentDate)){
			System.out.println("Your On Time .Please Visit the Library Again");
		}else {
			System.out.println("Great !! You Submitted the Book Before Return Date.Please Visit the Library Again");
		}
		
		//to update returned book availability as IN and return date as null
		int result = bookdaoimpl.update(id , option);
		if(result !=0) {
			System.out.println("You have sucessfully returned the book ");
			}
	}
	
	private double penalty(long daysCount) {
		System.out.println("Penalty for each day is $1.5 ");
		return daysCount * 1.5;
	}
}

