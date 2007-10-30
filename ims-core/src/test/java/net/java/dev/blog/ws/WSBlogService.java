/**
 * 
 */
package net.java.dev.blog.ws;

import net.java.dev.blog.BaseTestCase;
import net.java.dev.blog.service.BlogService;

import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.junit.Test;

/**
 * @author Jeff.Yu
 *
 */
public class WSBlogService extends BaseTestCase {
	

	@Test
	public void testPublish() throws Exception {
		ServerFactoryBean factory = new JaxWsServerFactoryBean();
		factory.setServiceClass(BlogService.class);
		factory.setServiceBean(applicationContext.getBean("blogService"));
		factory.setAddress("http://localhost:9000/Hello");
		factory.create();
		Thread.sleep(1 * 60 * 1000);
		
	
	}
	
}
