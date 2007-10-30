/**
 * 
 */
package net.java.dev.blog.service;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import net.java.dev.blog.BaseTestCase;
import net.java.dev.blog.dao.UserDAO;
import net.java.dev.blog.model.User;

/**
 * @author Jeff.Yu
 *
 */
public class ManagerServiceTest extends BaseTestCase {
	
	private ManagerService managerService;
	
	private UserDAO userDao;
	
	private User user;
	
	@Before
	public void setUp() throws Exception {
		managerService = new ManagerServiceImpl();
		userDao = EasyMock.createMock(UserDAO.class);
		
		user = new User();
		user.setLoginName("test");
	}
	
	@Test
	public void testCreateUserFlow() throws Exception {
		EasyMock.expect(userDao.getUserByLoginName(user.getLoginName())).andReturn(null);
		EasyMock.expect(userDao.createUser(user)).andReturn(user);
		EasyMock.replay(userDao);
		
		((ManagerServiceImpl)managerService).setUserDao(userDao);
		managerService.createUser(user);
		EasyMock.verify(userDao);
	}

	
	@Test
	public void testCreateDuplicateUserFlow() throws Exception {
		EasyMock.expect(userDao.getUserByLoginName(user.getLoginName())).andReturn(user);
		EasyMock.replay(userDao);
		((ManagerServiceImpl)managerService).setUserDao(userDao);
		try {
			managerService.createUser(user);
		}catch (AppBizException e) {
			assertTrue(true);
		}finally {
			EasyMock.verify(userDao);
		}
	}
}
