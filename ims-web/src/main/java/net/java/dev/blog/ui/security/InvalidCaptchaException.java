package net.java.dev.blog.ui.security;

import org.acegisecurity.AuthenticationException;

public class InvalidCaptchaException extends AuthenticationException {
	
	private static final long serialVersionUID = 1113L;
	
	public InvalidCaptchaException(String msg) {
		super(msg);
	}
	
    public InvalidCaptchaException(String msg, Throwable t) {
        super(msg, t);
    }
    
}
