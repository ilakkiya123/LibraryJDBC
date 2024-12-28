package com.library;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO {
	List<Book> getAll(int option) throws SQLException;
	Book getId(int id , int option) throws SQLException;
	int insert(Book book);
	int update(int id ,int option) throws SQLException;
	int delete(Book book);
}
