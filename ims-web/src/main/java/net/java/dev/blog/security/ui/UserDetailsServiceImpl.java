/**
 *
 */
package net.java.dev.blog.security.ui;

import net.java.dev.blog.service.AppBizException_Exception;
import net.java.dev.blog.service.ManagerService;
import net.java.dev.blog.service.User;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;

import org.springframework.dao.DataAccessException;


/**
 * @author Jeff.Yu
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    
	private ManagerService managerService;
	
	private SSOAuthenticationService ssoService;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
    	
    	SSOUserDetail user = ssoService.authenticate(userName);
    	
        if (user == null) {
            throw new UsernameNotFoundException("username is not found!");
        }

        try {
			User bloggingUser = managerService.getUserNoPassword(userName);
			user.setBloggingUser(bloggingUser);
		} catch (AppBizException_Exception e) {
			throw new RuntimeException("authenticated fail in blogging service");
		}

        return user;
    }

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}

	public void setSsoService(SSOAuthenticationService ssoService) {
		this.ssoService = ssoService;
	}
    
    
    
}
