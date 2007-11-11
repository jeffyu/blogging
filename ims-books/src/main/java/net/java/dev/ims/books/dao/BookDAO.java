/**
 * 
 */
package net.java.dev.ims.books.dao;

import net.java.dev.ims.books.model.Book;

/**
 * @author Jeff.Yu
 *
 */
public interface BookDAO {
	
	public Book createBook(Book book);
	
	public Book getBook(long bookID);
	
	public Book updateBook(Book book);
	
	public Book removeBook(long bookID);
	
}
