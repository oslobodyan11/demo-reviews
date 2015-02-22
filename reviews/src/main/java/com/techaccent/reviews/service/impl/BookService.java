package com.techaccent.reviews.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techaccent.reviews.dao.IBookDAO;
import com.techaccent.reviews.model.Book;
import com.techaccent.reviews.model.Comment;
import com.techaccent.reviews.service.IBookService;

/**
 * BookService class is the facade for 
 * library business logic. 
 * 
 * @author Oleksandr Slobodyan
 * @version 1.0
 * @since 2015-02-18
 */
@Service
public class BookService implements IBookService {
	@Autowired
	private IBookDAO bookDAO;
	
	@Override
	public List<Book> getBookList() {
		// TODO Auto-generated method stub
		return bookDAO.getBookList();
	}

	@Override
	public Book getBook(final Integer id) {
		return bookDAO.getBook(id);
	}

	@Override
	public void addBook(final Book newBook) {
		// TODO Auto-generated method stub
		bookDAO.addBook(newBook);

	}

	@Override
	public void addComment(final Integer bookId, final Comment newComment) {
		bookDAO.addComment(bookId, newComment);

	}

}
