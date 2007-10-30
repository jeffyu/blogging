/**
 * 
 */
package net.java.dev.blog.dao;

import java.util.Date;
import java.util.List;

import net.java.dev.blog.BaseDBUnitTest;
import net.java.dev.blog.model.User;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * @author Jeff.Yu
 *
 */
public class UserDaoTest extends BaseDBUnitTest {
	
	private UserDAO userDao;
	private User createdUser;
	
	@Before
	public void setUp() throws Exception {
		userDao = (UserDAO)applicationContext.getBean("userDao");
		
		createdUser = new User();
		createdUser.setLoginName("Jeff2");
		createdUser.setUserName("Jeff.YuChang");
		createdUser.setUserEmail("jeff.yuchang@gmail.com");
		createdUser.setUserDate(new Date());
		createdUser.setUserPassword("jeff@bj");
		createdUser.setAuthoritiesFromString("ROLE_USER,ROLE_ADMIN");
	}
	
	@Test
	public void testGetUsers() throws Exception {
		List<User> users = userDao.getUsers();
		assertEquals(1, users.size());
	}
	
	@Test
	public void testGetUser() throws Exception {
		User user = userDao.getUser(1);
		assertEquals(1, user.getUserID());
	}
	
	@Test
	public void testCreateAndRemoveUser() throws Exception {
		User addedUser = userDao.createUser(createdUser);
		assertEquals(createdUser.getUserName(), userDao.getUser(addedUser.getUserID()).getUserName());
		
		userDao.removeUser(addedUser);
		try{
			userDao.getUser(addedUser.getUserID());
			fail("should have exception");
		}catch (EmptyResultDataAccessException e) {
			
		}
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		User user = userDao.getUser(1);
		user.setUserName("JeffChanged");
		userDao.updateUser(user);
		
		assertEquals("JeffChanged", userDao.getUser(1).getUserName());
	}
	
	@Test
	public void testGetUserByLoginName() throws Exception {
		assertEquals("jeff", userDao.getUserByLoginName("jeff").getLoginName());
		assertNull(userDao.getUserByLoginName("NoUser"));
	}
	
	@Test
	public void testGetUserAuthorities() throws Exception {
		assertEquals(2, userDao.getUser(1).getAuthorities().size());
	}
	
}
