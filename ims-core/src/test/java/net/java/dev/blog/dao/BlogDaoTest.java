/**
 * 
 */
package net.java.dev.blog.dao;

import java.util.Date;
import java.util.List;

import net.java.dev.blog.BaseDBUnitTest;
import net.java.dev.blog.model.Blog;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * @author Jeff.Yu
 *
 */
public class BlogDaoTest extends BaseDBUnitTest {
	
	private BlogDAO blogDao;
	
	@Before
	public void setUp() throws Exception {
		blogDao = (BlogDAO)applicationContext.getBean("blogDao");
	}
	
	@Test
	public void testGetBlog() throws Exception {
		assertEquals(1, blogDao.getBlog(1).getBlogID());
	}
	
	@Test
	public void testGetBlogs() throws Exception {
		assertTrue(blogDao.getBlogs().size() > 1);
	}
	
	@Test
	public void testGetBlogsByUser() throws Exception {
		assertEquals(4, blogDao.getBlogs(1).size());
		assertEquals(0, blogDao.getBlogs(2).size());
	}
	
	@Test
	public void testUpdateBlog() throws Exception {
		Blog blog = blogDao.getBlog(1);
		blog.setBlogContent("ChangedBlog");
		blogDao.updateBlog(blog);
		
		assertEquals("ChangedBlog", blogDao.getBlog(1).getBlogContent());
	}
	
	@Test
	public void testCreateAndRemove() throws Exception {
		Blog blog = new Blog();
		blog.setBlogTitle("blogTitle");
		blog.setBlogContent("blogContent");
		blog.setBlogDate(new Date());
		blog.setUser(user);
		
		Blog addedBlog = blogDao.createBlog(blog);
		long addedBlogID = addedBlog.getBlogID();
		
		assertEquals(blog.getBlogTitle(), blogDao.getBlog(addedBlogID).getBlogTitle());
		
		blogDao.removeBlog(addedBlog);
		try {
			blogDao.getBlog(addedBlogID);
		}catch (EmptyResultDataAccessException e) {
			//
		}
	}
	
	@Test
	public void testGetAllBlogs() throws Exception {
		List<Blog> blogs = blogDao.getBlogs();
		assertTrue(blogs.size() > 0);
	}
	
	@Test
	public void testGetBlogsByLabel() throws Exception {
		List<Blog> blogs = blogDao.getBlogsByLabel(3);
		assertTrue(blogs.size() == 1);		
	}
}
