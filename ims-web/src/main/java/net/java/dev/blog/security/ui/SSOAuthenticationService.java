/**
 * 
 */
package net.java.dev.blog.security.ui;

import java.util.ArrayList;
import java.util.List;

import org.acegisecurity.GrantedAuthority;

/**
 * @author Jeff.Yu
 *
 */
public class SSOAuthenticationService {
	
	private static List<SSOUserDetail> ssoUsers = new ArrayList<SSOUserDetail>();
	
	static {
		
		SSOUserDetail ssoUser = new SSOUserDetail();
		ssoUser.setUsername("jeff");
		ssoUser.setPassword("jeff@iona");
		ssoUser.setAuthorities(new GrantedAuthority[]{new StringGrantedAuthority("ROLE_USER"), 
												   new StringGrantedAuthority("ROLE_ADMIN")});		
		
		ssoUsers.add(ssoUser);
		
	}
	
	
	public SSOUserDetail authenticate(String username) {
		
		for(SSOUserDetail user : ssoUsers) {
			if (user.getUsername().equals(username)){
				return user;
			}
		}
		
		return null;		
	}
	
}
