/**
 * 
 */
package net.java.dev.blog.dao;

import java.util.List;

import net.java.dev.blog.model.Comment;

/**
 * @author Jeff.Yu
 *
 */
public interface CommentDAO {
	
	public Comment getComment(long commentId);
	
	public Comment createComment(Comment comment);
	
	public void removeComment(Comment comment);
	
	public List<Comment> getComments(long blogID);
	
}
