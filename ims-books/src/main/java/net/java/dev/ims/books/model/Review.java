/**
 * 
 */
package net.java.dev.ims.books.model;

import java.util.Date;

/**
 * @author Jeff.Yu
 */
public class Review {
	
	private long reviewID;
	
	private User user;
	
	private Book book;
	
	private String reviewContent;
	
	private double reviewScore;
	
	private Date reviewedDate;

	public long getReviewID() {
		return reviewID;
	}

	public void setReviewID(long reviewID) {
		this.reviewID = reviewID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public double getReviewScore() {
		return reviewScore;
	}

	public void setReviewScore(double reviewScore) {
		this.reviewScore = reviewScore;
	}

	public Date getReviewedDate() {
		return reviewedDate;
	}

	public void setReviewedDate(Date reviewedDate) {
		this.reviewedDate = reviewedDate;
	}
	
	
}
