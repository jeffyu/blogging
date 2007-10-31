/**
 * 
 */
package net.java.dev.blog;

import junit.framework.Assert;
import net.java.dev.blog.model.Blog;
import net.java.dev.blog.model.User;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Jeff.Yu
 *
 */
public class BaseTestCase extends Assert {
	
	protected static ApplicationContext applicationContext;
	
	protected static final Long ADMIN_USER_ID = 1L;
	
	protected static final Long BLOG_ID = 1L;
	
	protected static final Long COMMENT_ID = 1L;
	
	protected static final Long LABEL_ID = 1L;
	
	protected static User user;
	
	protected static Blog firstBlog;
	
	
	@BeforeClass
	public static void initializeContext() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(new String[]{"/daoContext.xml", 
																"/servicesContext.xml", 
																"/securityContext.xml"});
	}
	
	@BeforeClass
	public static void setUpData() throws Exception {
		user = new User();
		user.setUserID(ADMIN_USER_ID);
		user.setLoginName("jeff");
		user.setUserPassword("jeff");
				
		firstBlog = new Blog();
		firstBlog.setUser(user);
		
	}
	
	
}
