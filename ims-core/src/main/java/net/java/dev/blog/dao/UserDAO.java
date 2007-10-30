/**
 * 
 */
package net.java.dev.blog.dao;

import java.util.List;

import net.java.dev.blog.model.User;

/**
 * @author Jeff.Yu
 *
 */
public interface UserDAO {
	
	public User getUser(long userId);
	
	public User getUserByLoginName(String name);
	
	public User getUserByNameAndPassword(String loginName, String password);
	
	public User createUser(User user);
	
	public void updateUser(User user);
	
	public void removeUser(User user);
	
	public List<User> getUsers();
	
}
