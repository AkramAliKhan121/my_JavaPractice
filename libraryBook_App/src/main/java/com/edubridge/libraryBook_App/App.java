package com.edubridge.libraryBook_App;

import java.util.List;
import java.util.Scanner;

import com.edubridge.libraryBook_App.model.Book;
import com.edubridge.libraryBook_App.dao.BookDao;
import com.edubridge.libraryBook_App.dao.BookDaoImpl;

public class App
{
    public static void main( String[] args )
    {

		BookDao dao = new BookDaoImpl();
		
		Scanner in = new Scanner(System.in);
		
		while(true) {
			System.out.println("My Library Book Application");
			System.out.println("------------------------");
			System.out.println("1. Add Book");
			System.out.println("2. View all Books");
			System.out.println("3. Search Book");
			System.out.println("4. Update Book");
			System.out.println("5. Delete Book");
			System.out.println("6. Delete All Books");
			System.out.println("0. Exit");
			System.out.println("------------------------------------------");
			System.out.println("Select Options");
			
			int option = in.nextInt();
			
			switch(option) {
			case 1:
				System.out.println("Add Book");
				System.out.println("------------------------------------------");
				System.out.println("Enter Book name");
				String book_name =in.next();
				System.out.println("Enter Author name");
				String book_author = in.next();
				System.out.println("Enter Book type");
				String book_type = in.next();
				
				Book nwbook = new Book();
				nwbook.setBook_name(book_name);
				nwbook.setBook_author(book_author);
				nwbook.setBook_type(book_type);
				
				int status = dao.addBook(nwbook);
				if(status>=1) {
					System.out.println("Book Added");
				}else {
					System.out.println("something went wrong");
				}
				break;
			case 2:
				System.out.println("View All Books");
				List<Book> Books = dao.getAllBooks();
				
				if(Books.size() != 0) {
					for (Book b : Books) {
						System.out.println(b.getBook_id()+"\t"+b.getBook_name()+"\t"+b.getBook_author()+"\t"+b.getBook_type());
					}
				}
				else {System.out.println("No Book Found");}
				break;
			case 3:
				System.out.println("Search Book");
				System.out.println("------------------------------------------");
				System.out.println("Enter the Book name to Search");
				String searchName =in.next();
				Book searchedBook = dao.getBook(searchName);
			    if (searchedBook != null) {
			        System.out.println(searchedBook.getBook_id() + "\t" + searchedBook.getBook_name() + "\t" + searchedBook.getBook_author() + "\t" + searchedBook.getBook_type());
			    } else {
			        System.out.println("book not found");
			    }

				break;
			case 4:
				System.out.println("Update book");
				System.out.println("------------------------------------------");
				System.out.println("Please Enter Book name to Update:");
			    String updateName = in.next();
			    Book updateBook = dao.getBook(updateName);
			    if (updateBook != null) {
			        System.out.println("Please Enter new Book name:");
			        String bookName = in.next();
			        System.out.println("Please Enter new Author name:");
			        String authorName = in.next();
			        System.out.println("Please Enter new Book type:");
			        String bookType = in.next();
			        updateBook.setBook_name(bookName);
			        updateBook.setBook_author(authorName);
			        updateBook.setBook_type(bookType);
			        int status1 = dao.updateBook(updateBook);
			        if (status1 >= 1) {
			            System.out.println("Book Updated Successfully");
			        } else {
			            System.out.println("Update Failed");
			        }
			    } else {
			        System.out.println("Book Not Found");
			    }
			    break;
			case 5:
				System.out.println("Delete Book");
		        System.out.println("------------------------------------------");
		        System.out.println("Please Enter Book name to Delete:");
		        String deleteName = in.next();
		        Book deleteBook = dao.deleteBook(deleteName);
		        if (deleteBook != null) {
		            System.out.println("Book Deleted Successfully");
		        } else {
		            System.out.println("Delete Failed");
		        }
		        break;
			case 6:
				dao.deleteAllBooks();
			    System.out.println("All Books Deleted");
			    break; 
			case 0:
				System.out.println("Thank You \n Have a Nice Day");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Option");
			
			
			}
		}

	}

    }

