/**
 * 
 */
package net.java.dev.ims.books.service;

import java.util.List;

import net.java.dev.ims.books.dao.UserDAO;
import net.java.dev.ims.books.model.User;

/**
 * @author Jeff.Yu
 *
 */
public class UserServiceImpl implements UserService {
	
	private UserDAO userDao;
	
	public User addUser(User user) throws AppBizException {
		return userDao.createUser(user);
	}


	public User getUser(long userID) throws AppBizException {
		return userDao.getUser(userID);
	}


	public List<User> getUsers() throws AppBizException {
		return userDao.getUsers();
	}


	public void removeUser(long userID) throws AppBizException {
		User user = userDao.getUser(userID);
		userDao.removeUser(user);
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	

}
