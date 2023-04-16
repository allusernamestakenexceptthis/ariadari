package com.gomilkyway.profile.adari.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.gomilkyway.profile.adari.handlers.CustomAccessDeniedHandler;

import static org.springframework.security.config.Customizer.withDefaults;
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
                                        "/font/**",
                                        "/register",
                                        "/login",
                                        "/home",
                                        "/status"
                                ).permitAll()
                                .anyRequest().denyAll()

                )

                .formLogin(form -> form
                                .loginPage("/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                )
                .csrf(withDefaults())
                .exceptionHandling(handling -> handling.accessDeniedHandler(new CustomAccessDeniedHandler("redirect:/?accessDenied")))
			;
		
		return http.build();

	}

}
