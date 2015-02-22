package com.techaccent.reviews.dao;

import java.util.List;

import com.techaccent.reviews.model.Book;
import com.techaccent.reviews.model.Comment;

/**
* BookDAO interface defines 
* a contract for Book entity persistence.
*
*
* @author  Oleksandr Slobodyan
* @version 1.0
* @since   2015-02-18 
*/

public interface IBookDAO {
	List<Book> getBookList();
	void addBook(Book newBook);
	Book getBook(Integer bookId);
	void addComment(Integer bookId, Comment newComment);
}
