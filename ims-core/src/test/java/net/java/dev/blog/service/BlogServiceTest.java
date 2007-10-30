/**
 * 
 */
package net.java.dev.blog.service;

import java.util.ArrayList;
import java.util.List;

import net.java.dev.blog.BaseTestCase;
import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.Label;
import net.java.dev.blog.model.User;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.context.SecurityContextImpl;
import org.acegisecurity.providers.AuthenticationProvider;
import org.acegisecurity.providers.ProviderManager;
import org.acegisecurity.providers.TestingAuthenticationProvider;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jeff.Yu
 *
 */
public class BlogServiceTest extends BaseTestCase {
	
	private BlogService blogService;
	
	protected Blog blog;
	
	private User user;
	
	private Label label;
	
	@Before
	public void setUp() throws Exception {
		blogService = (BlogService)applicationContext.getBean("blogService");
		
		user = new User();
		user.setUserID(1);
		
		label = new Label();
		label.setLabelID(1);
		label.setUser(user);
		
		TestingAuthenticationToken token = new TestingAuthenticationToken("jeff", "jeff",
										   new GrantedAuthority[]{new GrantedAuthorityImpl("ROLE_USER")});
		ProviderManager manager = (ProviderManager)applicationContext.getBean("authenticationManager");
		List<AuthenticationProvider> list = new ArrayList<AuthenticationProvider>();
		list.add(new TestingAuthenticationProvider());
		manager.setProviders(list);
		
        SecurityContextImpl secureContext = new SecurityContextImpl();
        secureContext.setAuthentication(token);
        SecurityContextHolder.setContext(secureContext);
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
	
}
