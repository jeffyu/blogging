/**
 * 
 */
package net.java.dev.ims.books.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Jeff.Yu
 *
 */
public class Book {
	
	private String bookTitle;
	
	private long bookID;
	
	private User publishedUser;
	
	private String bookAuthors;
	
	private Date publishedDate;
	
	private double bookPrice;
	
	private Date createdDate;
	
	private List<Review> reviews = new ArrayList<Review>();
	
	private List<Label> labels = new ArrayList<Label>();
	
	private double reviewScore;

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public long getBookID() {
		return bookID;
	}

	public void setBookID(long bookID) {
		this.bookID = bookID;
	}

	public User getPublishedUser() {
		return publishedUser;
	}

	public void setPublishedUser(User publishedUser) {
		this.publishedUser = publishedUser;
	}

	public String getBookAuthors() {
		return bookAuthors;
	}

	public void setBookAuthors(String bookAuthors) {
		this.bookAuthors = bookAuthors;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public double getReviewScore() {
		return reviewScore;
	}

	public void setReviewScore(double reviewScore) {
		this.reviewScore = reviewScore;
	}

	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}
	
}
