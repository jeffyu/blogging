/**
 * 
 */
package net.java.dev.blog.service;

import net.java.dev.blog.BaseDBUnitTest;
import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.User;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationManager;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.context.SecurityContextImpl;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * @author Jeff.Yu
 *
 */
public class ManagerSvcTest extends BaseDBUnitTest {
	
	private ManagerService managerService;
	
	private BlogService blogService;
	
	@BeforeClass
	public static void securityContext() throws Exception {
	   UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken
														(user.getLoginName(), user.getUserPassword());
		AuthenticationManager auth = (AuthenticationManager)applicationContext.getBean("authenticationManager");
		Authentication authen = auth.authenticate(token);
		
		SecurityContext securityContext = new SecurityContextImpl();
		securityContext.setAuthentication(authen);
		SecurityContextHolder.setContext(securityContext);
	}
	
	@Before
	public void securitySetUp() throws Exception {	   
	   managerService = (ManagerService) applicationContext.getBean("managerService");
	   blogService = (BlogService) applicationContext.getBean("blogService");
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		user.setUserName("Chang.Yu");
		User updateUser = managerService.updateUser(user);
		assertEquals(updateUser.getUserName(), user.getUserName());
	}
	
	@Test
	public void testPublishBlogAndRemove() throws Exception {
		Blog blog = new Blog();
		blog.setUser(user);
		blog.setBlogTitle("unit test");
		blog.setBlogContent("unit test");
		
		Blog addedBlog = managerService.publishBlog(blog, user);
		
		assertEquals("unit test", addedBlog.getBlogTitle());
		
		managerService.removeBlog(addedBlog.getBlogID(), user);
		
		try {
			blogService.getBlog(addedBlog.getBlogID());
		} catch (EmptyResultDataAccessException e) {
			assertTrue(true);
		}
		
	}
	
}
