package com.techaccent.reviews.model;

import java.util.ArrayList;
import java.util.List;

/**
* Book class is for Book model object. 
*  
* @author Oleksandr Slobodyan
* @version 1.0
* @since 2015-02-18
*/
public class Book {
	private Integer id; 
	private String name;
	private String author;
	private String publicationYear;
	private List<Comment> commentList;
	private Integer numberOfComments = 0;
	
	public Book() {
		this.commentList = new ArrayList<Comment>();
	}
	
	public Book(final String name, final String author, final String publicationYear) {
		super();
		this.name = name;
		this.id = name.hashCode();
		this.author = author;
		this.publicationYear = publicationYear;
		this.commentList = new ArrayList<Comment>();
		this.numberOfComments = 0;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	public Integer getNumberOfComments() {
		return numberOfComments;
	}
	public void addComment(final Comment comment) {
		this.commentList.add(comment);
		this.numberOfComments = this.numberOfComments + 1;
	}
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
		this.id = name.hashCode();
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(final String author) {
		this.author = author;
	}
	public String getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(final String publicationYear) {
		this.publicationYear = publicationYear;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(final List<Comment> commentList) {
		this.commentList = commentList;
	}
	
}
