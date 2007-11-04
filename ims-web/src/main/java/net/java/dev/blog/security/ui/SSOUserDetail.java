/**
 *
 */
package net.java.dev.blog.security.ui;


import net.java.dev.blog.service.User;

import org.acegisecurity.GrantedAuthority;

import org.acegisecurity.userdetails.UserDetails;


/**
 * @author Jeff.Yu
 *
 */
public class SSOUserDetail implements UserDetails {
    private static final long serialVersionUID = 1112L;
    
    private GrantedAuthority[] authorities;
    
    private User bloggingUser;
    
    private String password;
    
    private String username;

    public GrantedAuthority[] getAuthorities() {
    	return authorities;
    } 

    public void setAuthorities(GrantedAuthority[] authorities) {
		this.authorities = authorities;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
    	this.username = username;
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

	public User getBloggingUser() {
		return bloggingUser;
	}

	public void setBloggingUser(User bloggingUser) {
		this.bloggingUser = bloggingUser;
	}
       

}
