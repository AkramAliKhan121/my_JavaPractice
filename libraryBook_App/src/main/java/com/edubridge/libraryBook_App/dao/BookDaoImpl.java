package com.edubridge.libraryBook_App.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edubridge.libraryBook_App.model.Book;
import com.edubridge.libraryBook_App.utils.DBUtils;

public class BookDaoImpl implements BookDao {

	private static final String SELECT_BY_NAME = null;
	private static final Book Books = null;
	private Book b11;

		
	@Override
	public List<Book> getAllBooks() {
		
			String SELECT = "select * from library_book";
			Connection con = DBUtils.getconnection();
			List<Book> books = new ArrayList<Book>();
			
			try {
				PreparedStatement ps = con.prepareStatement(SELECT);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Book b = new Book();
					b.setBook_id(rs.getInt("book_id"));
					b.setBook_name(rs.getString("book_name"));
					b.setBook_author(rs.getString("book_author"));
					b.setBook_type(rs.getString("book_type"));
					books.add(b);
				}
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return books;}
	

	@Override
	public Book getBook(String searchName) {

			        Connection con = DBUtils.getconnection();
		            String SELECT_BY_NAME = "SELECT * FROM library_book WHERE book_name=?";

		            
		            Book  b1 = new Book();
			        try {
			            PreparedStatement ps = con.prepareStatement(SELECT_BY_NAME);
			            ps.setString(1, searchName);
			            ResultSet rs = ps.executeQuery();
			            if (rs.next()) {
			                b1.setBook_id(rs.getInt("book_id"));
			                b1.setBook_name(rs.getString("book_name"));
			                b1.setBook_author(rs.getString("book_author"));
			                b1.setBook_type(rs.getString("book_type"));
			            }
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
					return b1;
		}

	@Override
	public int addBook(Book b) {

			String INSERT = "insert into  library_book(book_id, book_name, book_author, book_type) values(?,?,?,?)";
			
			Connection con = DBUtils.getconnection();
			
			int status = 0;
			
			try {
				PreparedStatement ps = con.prepareStatement(INSERT);
				ps.setInt(1, b.getBook_id());
				ps.setString(2, b.getBook_name());
				ps.setString(3, b.getBook_author());
				ps.setString(4, b.getBook_type());			
				
				status = ps.executeUpdate();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			return status;
		}
		

	@Override
	public int updateBook(Book b) {
		 String UPDATE = "update library_book set book_name = ?, book_author = ?, book_type = ? where book_id = ?";
		    Connection con = DBUtils.getconnection();
		    int status = 0;
		    try {
		        PreparedStatement ps = con.prepareStatement(UPDATE);
		        ps.setString(1, b.getBook_name());
		        ps.setString(2, b.getBook_author());
		        ps.setString(3, b.getBook_type());
		        ps.setInt(4, b.getBook_id());
		        status = ps.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return status;

	}
	
	
	@Override
	public Book deleteBook(String bookName) {
	    String DELETE = "DELETE FROM library_book WHERE book_name = ?";
	    Connection con = null;
	    PreparedStatement ps = null;
	    Book deletedBook = null;

	    try {
	        con = DBUtils.getconnection();
	        ps = con.prepareStatement(DELETE);
	        ps.setString(1, bookName);
	        int status = ps.executeUpdate();

	        if (status > 0) {
	            deletedBook = new Book();
	            deletedBook.setBook_name(bookName); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return deletedBook;
	}



	@Override
	public void deleteAllBooks() {
		
		 String DELETE_ALL = "truncate table library_book";
		    Connection con = DBUtils.getconnection();
		    try {
		        PreparedStatement ps = con.prepareStatement(DELETE_ALL);
		        ps.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		
}
