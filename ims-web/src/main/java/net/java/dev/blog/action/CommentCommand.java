/**
 * 
 */
package net.java.dev.blog.action;

/**
 * @author Jeff.Yu
 *
 */
public class CommentCommand {
	
	private String userName;
	
	private String userEmail;
	
	private String commentContent;
	
	private Long blogID;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Long getBlogID() {
		return blogID;
	}

	public void setBlogID(Long blogID) {
		this.blogID = blogID;
	}
	
	
	
}
