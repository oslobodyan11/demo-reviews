package com.techaccent.reviews.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techaccent.reviews.model.Book;
import com.techaccent.reviews.model.Comment;
import com.techaccent.reviews.service.IBookService;

/**
 * BookController class implements back end RESTFull web services for the single
 * page application front end.
 *
 *
 * @author Oleksandr Slobodyan
 * @version 1.0
 * @since 2015-02-18
 */

@RestController
@RequestMapping("/reviews")
public class BookController {

	@Autowired
	private IBookService bookService;

	@RequestMapping(value = "/books/{bookId}", method = RequestMethod.GET)
	public Book getBook(@PathVariable final Integer bookId) {
		return bookService.getBook(bookId);
	}

	@RequestMapping(value = "/newbook", method = RequestMethod.POST)
	public void addBook(@RequestBody final Book newBook) {
		bookService.addBook(newBook);
	}

	@RequestMapping(value = "/library", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Book> getBookList() {
		return bookService.getBookList();
	}

	@RequestMapping(value = "/newcomment", method = RequestMethod.POST)
	public void addComment(@RequestBody final Comment newComment) {
		bookService.addComment(newComment.getBookId(), newComment);
	}

}
