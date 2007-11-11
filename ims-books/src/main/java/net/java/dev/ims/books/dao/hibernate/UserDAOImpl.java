/**
 * 
 */
package net.java.dev.ims.books.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import net.java.dev.ims.books.dao.UserDAO;
import net.java.dev.ims.books.model.User;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Jeff.Yu
 *
 */
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {

	public User createUser(User user) {
		Serializable id = getHibernateTemplate().save(user);
		return (User)getHibernateTemplate().get(User.class, id);
	}


	public User getUser(long userId) {
		return (User)getHibernateTemplate().get(User.class, userId);
	}

	@SuppressWarnings("unchecked")
	public User getUserByLoginName(String name) {
		List<User> users = getHibernateTemplate().find("from User as u where u.loginName = ?", new Object[]{name});
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public User getUserByNameAndPassword(String loginName, String password) {
		List<User> users = getHibernateTemplate().find("from User as u where u.loginName = ? and u.userPassword = ?", new Object[]{loginName, password});
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		return (List<User>) getHibernateTemplate().find("from User as u order by u.userDate asc");
	}


	public void removeUser(User user) {
		getHibernateTemplate().delete(user);
	}


	public void updateUser(User user) {
		getHibernateTemplate().update(user);
	}

}
