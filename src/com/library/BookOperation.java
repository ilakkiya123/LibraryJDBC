package com.library;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.library.impl.*;
import com.library.*;

public interface BookOperation {
	void borrowBook(BookDAOImpl bookdaoimpl , int option) throws SQLException;
	Book findBook(List<Book> bookcollection, String title , Scanner scan, int option);
	void returnBook(BookDAOImpl bookdaoimpl, int option) throws SQLException;
}
