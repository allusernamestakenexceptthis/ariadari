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

import com.gomilkyway.profile.adari.handlers.CustomAccessDeniedHandler;
import com.gomilkyway.profile.adari.user.UserRole;

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
			.authorizeHttpRequests(requests -> requests
                .requestMatchers("/admin/**").hasAuthority(UserRole.ADMIN.name())
                .requestMatchers("/members/**").authenticated()
                .requestMatchers(
                    "/",
                    "/favicon.ico",
                    "/error",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/index.html",
                    "/resources/**",
                    "/get/**",
                    "/main/**",
                    "/front/**",
                    "/webjars/**",
                    "/register",
                    "/login",
                    "/home",
                    "/status"
                ).permitAll()
                .anyRequest().denyAll()
                
            )
            
			.formLogin(form-> form
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                       )
			.csrf().disable()
            .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler("redirect:/?accessDenied"))
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

}
