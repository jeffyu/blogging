/**
 * 
 */
package net.java.dev.blog.dao;

import java.util.Date;

import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.Comment;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;


/**
 * @author Jeff.Yu
 *
 */
public class CommentDaoTest extends BaseDBUnitTest {
	
	private CommentDAO commentDao;
	
	@Before
	public void setUp() throws Exception {
		commentDao = (CommentDAO)applicationContext.getBean("commentDao");
	}
	
	@Test
	public void testGetComment() throws Exception {
		assertEquals(1, commentDao.getComment(1).getCommentID());
	}
	
	@Test
	public void testCreateAndRemove() throws Exception {
		Comment comment = new Comment();
		Blog blog = new Blog();
		blog.setBlogID(1);
		comment.setBlog(blog);
		comment.setCommentDate(new Date());
		comment.setCommentContent("CommentContent");
		
		Comment addedComment = commentDao.createComment(comment);
		long addedCommentID = addedComment.getCommentID();
		assertEquals(comment.getCommentContent(), commentDao.getComment(addedCommentID).getCommentContent());
		
		commentDao.removeComment(addedComment);
		try {
			commentDao.getComment(addedCommentID);
		} catch (EmptyResultDataAccessException e) {
			//
		}
	}
	
	@Test
	public void getAllComments() throws Exception {
		assertTrue(commentDao.getComments(1).size() > 1);
	}
	
}
