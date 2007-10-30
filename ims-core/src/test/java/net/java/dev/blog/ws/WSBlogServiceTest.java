/**
 * 
 */
package net.java.dev.blog.ws;

import java.util.List;

import net.java.dev.blog.BaseCXFServiceTest;
import net.java.dev.blog.model.Blog;
import net.java.dev.blog.service.BlogService;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Jeff.Yu
 *
 */
public class WSBlogServiceTest extends BaseCXFServiceTest {	
	
	private static BlogService blogService;
	
	@BeforeClass
	public static void setUp() throws Exception {
		publishEndpoint();
		getClientEndpoint();
	}
	
	
	private static void publishEndpoint() throws Exception {
		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		factory.setServiceClass(BlogService.class);
		factory.setServiceBean(applicationContext.getBean("blogService"));
		factory.setAddress("local://BlogService");
		Server server = factory.create();
		server.start();
	}
	
	private static void getClientEndpoint() throws Exception {
		JaxWsProxyFactoryBean client = new JaxWsProxyFactoryBean();
		client.setServiceClass(BlogService.class);
		client.setAddress("local://BlogService");
		blogService = (BlogService) client.create();		
	}
	
	@Test
	public void testGetAllBlogs() throws Exception {
		List<Blog> blogs = blogService.getAllBlogs();
		assertTrue(blogs.size() > 1);
	}
	
	
	
}
