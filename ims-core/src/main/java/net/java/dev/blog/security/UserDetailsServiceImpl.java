/**
 *
 */
package net.java.dev.blog.security;

import net.java.dev.blog.dao.UserDAO;
import net.java.dev.blog.model.User;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;

import org.springframework.dao.DataAccessException;


/**
 * @author Jeff.Yu
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserDAO userDao;

    public UserDetails loadUserByUsername(String userName)
        throws UsernameNotFoundException, DataAccessException {
        User user = userDao.getUserByLoginName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("username is not found!");
        }

        UserDetails userDetail = new UsersDetailImpl(user);

        return userDetail;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }
}
