/**
 * 
 */
package net.java.dev.blog.ui.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.ui.webapp.AuthenticationProcessingFilter;

import com.octo.captcha.service.CaptchaService;

/**
 * @author Jeff.Yu
 *
 */
public class AuthProcessingFilter extends AuthenticationProcessingFilter {
	
	public static final String ACEGI_SECURITY_FORM_JCAPTCHA_KEY = "j_captcha";
	
	private CaptchaService captchaService;

	public void setCaptchaService(CaptchaService captchaService) {
		this.captchaService = captchaService;
	}
	
	public Authentication attemptAuthentication(HttpServletRequest request) throws AuthenticationException {
		String captchaValue = this.obtainJcaptchaValue(request);
		if (captchaValue == null) {
			throw new InvalidCaptchaException("Not get the captcha value");
		} 
		
		HttpSession session = request.getSession();
		if (!captchaService.validateResponseForID(session.getId(), captchaValue)) {
			throw new InvalidCaptchaException("captcha authentication failed");
		}
		return super.attemptAuthentication(request);
	}
	
	public String obtainJcaptchaValue(HttpServletRequest request) {
		return request.getParameter(ACEGI_SECURITY_FORM_JCAPTCHA_KEY);
	}
	
}
