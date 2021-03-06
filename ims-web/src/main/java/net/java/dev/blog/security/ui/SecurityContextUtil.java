/**
 * 
 */
package net.java.dev.blog.security.ui;

import net.java.dev.blog.service.User;

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;


/**
 * @author Jeff.Yu
 *
 */
public class SecurityContextUtil {
	
	private SecurityContextUtil(){
	}
	
	public static User getBloggingUser() {		
		User currentUser = null;
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication authentication = ctx.getAuthentication();
		if (authentication.getPrincipal() instanceof UserDetails) {
			currentUser = ((SSOUserDetail)authentication.getPrincipal()).getBloggingUser();
		} else if (authentication.getDetails() instanceof UserDetails) {
			currentUser = ((SSOUserDetail)authentication.getDetails()).getBloggingUser();
		} else {
			throw new AccessDeniedException("User not properly authenticated.");
		}
		return currentUser;
	}
	
}
