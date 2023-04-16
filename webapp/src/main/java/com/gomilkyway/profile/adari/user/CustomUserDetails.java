package com.gomilkyway.profile.adari.user;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails{
    

    public String getName();
    public String getEmail();



}
