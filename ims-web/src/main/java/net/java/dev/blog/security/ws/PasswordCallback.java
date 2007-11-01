/**
 *
 */
package net.java.dev.blog.security.ws;

import org.apache.ws.security.WSPasswordCallback;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;


/**
 * @author Jeff.Yu
 *
 */
public class PasswordCallback implements CallbackHandler {
    /* (non-Javadoc)
     * @see javax.security.auth.callback.CallbackHandler#handle(javax.security.auth.callback.Callback[])
     */
    public void handle(Callback[] callbacks)
        throws IOException, UnsupportedCallbackException {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        pc.setPassword("jeff");
    }
}
