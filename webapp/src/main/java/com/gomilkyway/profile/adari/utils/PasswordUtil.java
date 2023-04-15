package com.gomilkyway.profile.adari.utils;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil{

    private static final int PASSWORDSTRENGTH = 10;
    
    private PasswordUtil() {
        
    }
	

	@Bean
    public static PasswordEncoder passwordEncoder() {
        String idForEncode = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(PASSWORDSTRENGTH, new SecureRandom());
        encoders.put(idForEncode, passwordEncoder);

        DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(passwordEncoder);
        return delegatingPasswordEncoder;
    }

	public static String encrypt(String password) {
        return passwordEncoder().encode(password);
	}

}