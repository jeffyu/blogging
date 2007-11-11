/**
 * 
 */
package net.java.dev.ims.books.service;

import java.util.List;

import net.java.dev.ims.books.model.User;

/**
 * @author Jeff.Yu
 *
 */
public interface UserService {
	
	public List<User> getUsers() throws AppBizException;
	
	public User getUser(long userID) throws AppBizException;
	
	public User addUser(User user) throws AppBizException;
	
	public void removeUser(long userID) throws AppBizException;

}
