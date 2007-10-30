/**
 * 
 */
package net.java.dev.blog.ws;

import java.util.List;

import junit.framework.Assert;
import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.Label;
import net.java.dev.blog.model.User;
import net.java.dev.blog.service.BlogService;
import net.java.dev.blog.service.ManagerService;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Jeff.Yu
 *
 */
public class WebServiceClient extends Assert {
	
	private User user;
	
	private Label label;
	
	private Blog blog;
	
	private ApplicationContext applicationContext;
	
	@Before
	public void setUp() throws Exception {
		user = new User();
		user.setUserID(1);
		
		label = new Label();
		label.setLabelID(1);
		label.setUser(user);
		
		blog = new Blog();
		blog.setBlogContent("Web Service Blog Content");
		blog.setBlogTitle("web service blog");
		blog.setUser(user);
		
		applicationContext = new ClassPathXmlApplicationContext("/wsClient.xml");
	}
	
	@Test
	@Ignore
	public void testRunBlogService() throws Exception {		
		BlogService blogService = (BlogService)applicationContext.getBean("client_blogService");
		
		List<Blog> blogs = blogService.getBlogs(user);
		System.out.println(blogs.get(0).getBlogTitle());
	}
	
	
	@Test
	public void testUpdateUser() throws Exception {		
		ManagerService manager = (ManagerService)applicationContext.getBean("client_managerService");
		user.setUserEmail("jeff.yuchang@gmail.com");
		user.setUserPassword("jeff");
		user.setUserName("jeff.yuchang");
		manager.updateUser(user);
	}
	
}
