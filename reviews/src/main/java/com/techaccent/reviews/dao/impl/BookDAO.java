package com.techaccent.reviews.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techaccent.reviews.dao.IBookDAO;
import com.techaccent.reviews.model.Book;
import com.techaccent.reviews.model.Comment;

/**
 * BookDAO class implements persistence layer for the Book object.
 *
 *
 * @author Oleksandr Slobodyan
 * @version 1.0
 * @since 2015-02-18
 */

@Service
public class BookDAO implements IBookDAO {

	@Autowired
	private ServletContext context;

	@SuppressWarnings("all")
	public List<Book> getBookList() {
		HashMap<Integer, Book> libraryStore = (HashMap<Integer, Book>) context
				.getAttribute("libraryStore");
		if (libraryStore == null) {
			libraryStore = new HashMap<Integer, Book>();
			libraryStore
					.put("Foundation, Foundation and Empire, Second Foundation"
							.hashCode(),
							new Book(
									"Foundation, Foundation and Empire, Second Foundation",
									"Isaac Asimov", "1970"));
			libraryStore.put("The Journey of Joenes".hashCode(), new Book(
					"The Journey of Joenes", "Robert Sheckley", "1972"));
			libraryStore.put("The Adventures of Tom Sawyer".hashCode(),
					new Book("The Adventures of Tom Sawyer", "Mark Twain",
							"1973"));

			libraryStore.get(
					"Foundation, Foundation and Empire, Second Foundation"
							.hashCode()).addComment(
					new Comment(
							"Foundation, Foundation and Empire, Second Foundation"
									.hashCode(), "", "Very impressive.",
							new Date()));
			context.setAttribute("libraryStore", libraryStore);

		}
		return new ArrayList<Book>(libraryStore.values());
	}

	@SuppressWarnings("all")
	public Book getBook(final Integer bookId) {
		HashMap<Integer, Book> libraryStore = (HashMap<Integer, Book>) context
				.getAttribute("libraryStore");
		return (Book) libraryStore.get(bookId);
	}

	@SuppressWarnings("all")
	public void addBook(final Book newBook) {
		HashMap<Integer, Book> libraryStore = (HashMap<Integer, Book>) context
				.getAttribute("libraryStore");
		if (libraryStore == null) {
			libraryStore = new HashMap<Integer, Book>();
			context.setAttribute("libraryStore", libraryStore);
		}

		libraryStore.put(newBook.getName().hashCode(), newBook);
	}

	@SuppressWarnings("all")
	public void addComment(final Integer bookId, final Comment newComment) {
		HashMap<Integer, Book> libraryStore = (HashMap<Integer, Book>) context
				.getAttribute("libraryStore");
		libraryStore.get(bookId).addComment(newComment);

	}

}
