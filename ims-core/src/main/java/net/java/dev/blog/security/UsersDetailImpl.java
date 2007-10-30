/**
 * 
 */
package net.java.dev.blog.security;

import java.util.ArrayList;
import java.util.List;

import net.java.dev.blog.model.User;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;

/**
 * @author Jeff.Yu
 *
 */
public class UsersDetailImpl implements UserDetails {

	private static final long serialVersionUID = 1112L;
	
	private User user;
	
	public UsersDetailImpl(User user) {
		this.user = user;
	}
	
	
	public GrantedAuthority[] getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(String role: user.getAuthorities()) {
			GrantedAuthority authority = new StringGrantedAuthority(role);
			authorities.add(authority);
		}		
		GrantedAuthority[] a = new GrantedAuthority[]{};		
		return authorities.toArray(a);
	}

	/* (non-Javadoc)
	 * @see org.acegisecurity.userdetails.UserDetails#getPassword()
	 */
	public String getPassword() {
		return user.getUserPassword();
	}

	/* (non-Javadoc)
	 * @see org.acegisecurity.userdetails.UserDetails#getUsername()
	 */
	public String getUsername() {
		return user.getUserName();
	}

	/* (non-Javadoc)
	 * @see org.acegisecurity.userdetails.UserDetails#isAccountNonExpired()
	 */
	public boolean isAccountNonExpired() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.acegisecurity.userdetails.UserDetails#isAccountNonLocked()
	 */
	public boolean isAccountNonLocked() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.acegisecurity.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.acegisecurity.userdetails.UserDetails#isEnabled()
	 */
	public boolean isEnabled() {
		return true;
	}
	
	public User getUser() {
		return this.user;
	}

}
