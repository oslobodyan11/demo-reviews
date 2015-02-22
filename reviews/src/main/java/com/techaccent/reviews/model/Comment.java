package com.techaccent.reviews.model;

import java.util.Date;

/**
* Comment class is for Comment model object. 
*  
* @author Oleksandr Slobodyan
* @version 1.0
* @since 2015-02-18
*/
public class Comment {
	private Integer bookId;
	private String nickName;
	private String comment;
	private Date commentDate;

	public Comment() {
		
	}
	public Comment (final Integer bookId, final String nickName, final String comment, final Date commentDate) {
		this.bookId = bookId;
		this.nickName = nickName;
		this.comment = comment;
		this.commentDate = commentDate;
	}

	
	public Integer getBookId() {
		return bookId;
	}


	public void setBookId(final Integer bookId) {
		this.bookId = bookId;
	}


	public String getNickName() {
		return nickName;
	}
	public void setNickName(final String nickName) {
		this.nickName = nickName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(final String comment) {
		this.comment = comment;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(final Date commentDate) {
		this.commentDate = commentDate;
	}
}
