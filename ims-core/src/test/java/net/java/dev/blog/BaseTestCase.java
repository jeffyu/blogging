/**
 * 
 */
package net.java.dev.blog;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Jeff.Yu
 *
 */
public class BaseTestCase extends Assert {
	
	protected static ApplicationContext applicationContext;
	
	@BeforeClass
	public static void initializeContext() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(new String[]{"/daoContext.xml", 
																"/servicesContext.xml", 
																"/securityContext.xml"});
	}
	
}
