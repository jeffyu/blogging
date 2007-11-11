/**
 * 
 */
package net.java.dev.ims.books.dao;

import java.util.Date;
import java.util.List;

import net.java.dev.ims.books.BaseTestCase;
import net.java.dev.ims.books.model.User;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jeff.Yu
 *
 */
public class UserDAOTest extends BaseTestCase {
	
	private UserDAO userDAO;
	
	@Before
	public void setUp() throws Exception {
		userDAO = (UserDAO)applicationContext.getBean("userDao");
	}
	
	@Test
	public void testGetUser() throws Exception {
		User user = userDAO.getUser(1);
		assertEquals("jeff", user.getLoginName());
	}
	
	@Test
	public void testGetAllUsers() throws Exception {
		List<User> users = userDAO.getUsers();
		assertNotNull(users);
		assertTrue(users.size() > 0);
	}
	
	@Test
	public void testGetUserByLoginName() throws Exception {
		User user = userDAO.getUserByLoginName("jeff");
		assertEquals("jeff", user.getLoginName());
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		User user = userDAO.getUserByLoginName("jeff");
		user.setUserName("Jeff Yu");
		
		userDAO.updateUser(user);
		user = userDAO.getUser(1);
		assertEquals("Jeff Yu", user.getUserName());
	}
	
	@Test
	public void testAddAndRemoveUser() throws Exception {
		User user = new User();
		user.setLoginName("jeff2");
		user.setUserPassword("jeff2");
		user.setUserDate(new Date());
		user.setUserName("jeff2");
		user.setAuthoritiesFromString("ROLE_USER");
		
		User addedUser = userDAO.createUser(user);
		assertEquals("jeff2", addedUser.getLoginName());
		
		userDAO.removeUser(addedUser);
		addedUser = userDAO.getUser(addedUser.getUserID());
		assertNull(addedUser);
		
	}

}
