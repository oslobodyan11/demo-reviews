package com.techaccent.reviews.service;

import java.util.List;

import com.techaccent.reviews.model.Book;
import com.techaccent.reviews.model.Comment;

/**
* IBookService interface defines 
* a contract for Book entity.
*
*
* @author  Oleksandr Slobodyan
* @version 1.0
* @since   2015-02-18 
*/
public interface IBookService {
	List<Book> getBookList();
	Book getBook(Integer id);
	void addBook(Book newBook);
	void addComment(Integer bookId, Comment newComment);
}
