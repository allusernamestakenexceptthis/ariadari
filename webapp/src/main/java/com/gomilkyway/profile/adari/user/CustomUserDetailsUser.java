package com.gomilkyway.profile.adari.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetailsUser extends User implements CustomUserDetails {

    private static final long serialVersionUID = 1L;

    public final String name;
    public final String email;

    public CustomUserDetailsUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.name = "";
        this.email = "";
    }

    public CustomUserDetailsUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String name, String email) {
        super(username, password, authorities);
        this.name = name;
        this.email = email;
    }

    public String getName() {
		return this.name;
	}

    public String getEmail() {
		return this.email;
	}

}
