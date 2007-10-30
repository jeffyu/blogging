/**
 * 
 */
package net.java.dev.blog.service;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationManager;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.context.SecurityContextImpl;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.junit.Before;
import org.junit.Test;

import net.java.dev.blog.BaseDBUnitTest;
import net.java.dev.blog.model.User;

/**
 * @author Jeff.Yu
 *
 */
public class ManagerSvcTest extends BaseDBUnitTest {
	
	private ManagerService managerService;
	
	@Before
	public void securitySetUp() throws Exception {
	   UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken
															(user.getLoginName(), user.getUserPassword());
	   AuthenticationManager auth = (AuthenticationManager)applicationContext.getBean("authenticationManager");
	   Authentication authen = auth.authenticate(token);
	   
	   SecurityContext securityContext = new SecurityContextImpl();
	   securityContext.setAuthentication(authen);
	   SecurityContextHolder.setContext(securityContext);
	   
	   managerService = (ManagerService) applicationContext.getBean("managerService");
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		user.setUserName("Chang.Yu");
		User updateUser = managerService.updateUser(user);
		assertEquals(updateUser.getUserName(), user.getUserName());
	}
	
}
