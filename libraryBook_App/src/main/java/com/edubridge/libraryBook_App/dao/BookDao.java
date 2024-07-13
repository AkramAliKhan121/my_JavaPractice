package com.edubridge.libraryBook_App.dao;

import java.util.List;
import com.edubridge.libraryBook_App.model.Book;

public interface BookDao {
	
	List<Book> getAllBooks();

	int addBook(Book b);

	Book getBook(String name);

	int updateBook(Book b);

	Book deleteBook(String deleteName);

	void deleteAllBooks();


}
