/**
 * 
 */
package net.java.dev.ims.books.security;

import net.java.dev.ims.books.model.User;

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
	
	public static User getCurrentUser() {		
		User currentUser = null;
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication authentication = ctx.getAuthentication();
		if (authentication.getPrincipal() instanceof UserDetails) {
			currentUser = ((UsersDetailImpl)authentication.getPrincipal()).getUser();
		} else if (authentication.getDetails() instanceof UserDetails) {
			currentUser = ((UsersDetailImpl)authentication.getDetails()).getUser();
		} else {
			throw new AccessDeniedException("User not properly authenticated.");
		}
		return currentUser;
	}
	
}
