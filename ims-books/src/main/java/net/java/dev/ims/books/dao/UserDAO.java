/**
 *
 */
package net.java.dev.ims.books.dao;

import net.java.dev.ims.books.model.User;

import java.util.List;


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
