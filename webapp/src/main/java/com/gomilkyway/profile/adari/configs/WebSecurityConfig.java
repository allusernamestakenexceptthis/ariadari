/*
 * Copyright 2023 Ari Adari
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gomilkyway.profile.adari.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import com.gomilkyway.profile.adari.handlers.CustomAccessDeniedHandler;

import static org.springframework.security.config.Customizer.withDefaults;
import com.gomilkyway.profile.adari.user.UserRole;
import com.gomilkyway.profile.adari.user.UserService;

/**
 * Configuration class for Spring Security
 * 
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserService userService;

    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    } 

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
                .userDetailsService(userService)
                .authorizeHttpRequests(requests -> requests
                                .requestMatchers("/admin/**").hasAuthority(UserRole.ADMIN.name())
                                .requestMatchers("/members/**").authenticated()
                                .requestMatchers(
                                        "/",
                                        "/favicon.ico",
                                        "/logom.png",
                                        "/error",
                                        "/css/**",
                                        "/js/**",
                                        "/images/**",
                                        "/index.html",
                                        "/resources/**",
                                        "/get/**",
                                        "/main/**",
                                        "/front/**",
                                        "/uimages/**",
                                        "/webjars/**",
                                        "/font/**",
                                        "/register",
                                        "/login",
                                        "/home",
                                        "/status",
                                        "/assets/**"
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
