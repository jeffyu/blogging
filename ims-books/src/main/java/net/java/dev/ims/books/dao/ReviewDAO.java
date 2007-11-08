/**
 * 
 */
package net.java.dev.ims.books.dao;

import java.util.List;

import net.java.dev.ims.books.model.Review;

/**
 * @author Jeff.Yu
 *
 */
public interface ReviewDAO {
	
	public Review addReview(Review review);
	
	public void updateReview(Review review);
	
	public void RemoveReview(long reviewID);
	
	public Review getReview(long reviewID);
	
	public List<Review> getReviews(long bookID);
	
}
