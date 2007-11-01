/**
 *
 */
package net.java.dev.blog.ws;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationManager;

import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.context.SecurityContextImpl;

import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;

import org.apache.ws.security.WSPasswordCallback;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;


/**
 * @author Jeff.Yu
 *
 */
public class PlainPasswordAuth implements CallbackHandler {
    private AuthenticationManager authenticationManager;

    /* (non-Javadoc)
     * @see javax.security.auth.callback.CallbackHandler#handle(javax.security.auth.callback.Callback[])
     */
    public void handle(Callback[] callbacks)
        throws IOException, UnsupportedCallbackException {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

        String user = pc.getIdentifer();
        String password = pc.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user,
                password);
        Authentication authen = authenticationManager.authenticate(token);

        SecurityContext securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(authen);
        SecurityContextHolder.setContext(securityContext);
    }

    public void setAuthenticationManager(
        AuthenticationManager newAuthenticationManager) {
        this.authenticationManager = newAuthenticationManager;
    }
}
