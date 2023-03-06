package com.gomilkyway.profile.adari.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	/**
	 * Configure the security filter chain
	 * 
	 * @param http
	 * 		  the http security
	 * @return SecurityFilterChain
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests(requests -> requests.requestMatchers("/members/**").authenticated().anyRequest().permitAll())
			.formLogin(form-> form.loginPage("/login"))
			.csrf().disable()
			;
		/*
		http
			.authorizeHttpRequests((requests) -> requests.requestMatchers("/", "/home", "/js/*","/css/*","/img/*", "/fonts/*", "/index.html").permitAll().anyRequest().authenticated())
				
			.formLogin((form)-> form.loginPage("/login").permitAll())

			.logout((logout) -> logout.permitAll());*/
		
		return http.build();

	}

	/**
	 * Configure the user details service
	 * 
	 * @return UserDetailsService
	 */
    /*
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder().username("user").password(passwordEncoderz().encode("password")).roles("USER").build();
		return new InMemoryUserDetailsManager(user);
	}*/

	/**
	 * Configure the password encoder
	 * 
	 * @return BCryptPasswordEncoder
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoderz() {
		return new BCryptPasswordEncoder();
	}

}
