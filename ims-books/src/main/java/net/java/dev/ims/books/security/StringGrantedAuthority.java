package net.java.dev.ims.books.security;

import org.acegisecurity.GrantedAuthority;


public class StringGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = 1111L;
    private String role;

    public StringGrantedAuthority(String role) {
        this.role = role;
    }

    public String getAuthority() {
        return this.role;
    }
}
