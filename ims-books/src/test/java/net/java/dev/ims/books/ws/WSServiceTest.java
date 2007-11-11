/**
 * 
 */
package net.java.dev.ims.books.ws;

import net.java.dev.ims.books.BaseTestCase;
import net.java.dev.ims.books.service.UserService;
import net.java.dev.ims.books.service.UserServiceImpl;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jeff.Yu
 *
 */
public class WSServiceTest extends BaseTestCase {
	
	@Before
	public void setUp() throws Exception {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(UserService.class);
        sf.setResourceProvider(UserServiceImpl.class, new SingletonResourceProvider());
        sf.setAddress("http://localhost:9080/");
        sf.create();   
	}
	
	@Test
	public void runServiceTest() throws Exception {
		
	}
	
}
