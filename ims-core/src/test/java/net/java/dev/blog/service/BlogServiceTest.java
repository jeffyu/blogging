/**
 * 
 */
package net.java.dev.blog.service;

import java.util.List;

import net.java.dev.blog.BaseDBUnitTest;
import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.Label;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jeff.Yu
 *
 */
public class BlogServiceTest extends BaseDBUnitTest {
	
	private BlogService blogService;
	
	protected Blog blog;
	
	private Label label;
	
	@Before
	public void setUp() throws Exception {
		blogService = (BlogService)applicationContext.getBean("blogService");
		
		label = new Label();
		label.setLabelID(1);
		label.setUser(user);
		
	}
	
	
	@Test
	public void testGetBlogs() throws Exception {
		List<Blog> blogs = blogService.getAllBlogs();
		assertTrue(blogs.size() >= 1);
	}
	
	@Test
	public void testGetBlogsByLabel() throws Exception {
		List<Blog> blogs = blogService.getBlogsByLabel(label.getLabelID());
		assertTrue(blogs.size() > 1);		
	}
	
	@Test
	public void testGetBlogsByLabel3() throws Exception {
		List<Blog> blogs = blogService.getBlogsByLabel(3);
		assertTrue(blogs.size() > 0);		
	}
	
	@Test
	public void testGetBlog() throws Exception {
		Blog blog = blogService.getBlog(1);
		assertEquals(blog.getBlogID(), 1);
		assertEquals(2, blog.getCommentCount());
	}
	
	@Test
	public void testGetLabels() throws Exception {
		List<Label> labels = blogService.getLabels();
		assertTrue(labels.size() > 1);
	}
	
}
