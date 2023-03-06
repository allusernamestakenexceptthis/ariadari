package com.gomilkyway.profile.adari.utils;

import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil{

    private static final int PASSWORDSTRENGTH = 10;
    
    private PasswordUtil() {
        
    }
	

	@Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(PASSWORDSTRENGTH, new SecureRandom());
    }

	public static String encrypt(String password) {
        return passwordEncoder().encode(password);
	}

}