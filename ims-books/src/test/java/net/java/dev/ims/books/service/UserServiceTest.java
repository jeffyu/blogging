/**
 * 
 */
package net.java.dev.ims.books.service;

import java.util.List;

import net.java.dev.ims.books.BaseDBUnitTest;
import net.java.dev.ims.books.model.User;
import net.java.dev.ims.books.service.UserService;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jeff.Yu
 *
 */
public class UserServiceTest extends BaseDBUnitTest {
	
	private UserService userService;
	
	@Before
	public void setUp() throws Exception {
		userService = (UserService)applicationContext.getBean("userService");
	}
	
	@Test
	public void getUsers() throws Exception {
		List<User> users = userService.getUsers();
		assertTrue(users.size() > 0);
	}
	
}
