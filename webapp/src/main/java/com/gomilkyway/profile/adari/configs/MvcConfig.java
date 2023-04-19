/*
 * Copyright 2023 Ari Adari
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gomilkyway.profile.adari.configs;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gomilkyway.profile.adari.handlers.AdminIntercepterHandler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

/**
 * MVC configuration class
 * the purpose of this class is to configure the MVC
 * and add view controllers to the registry
 * and intercepters and resource handlers
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	/**
	 * Add view controllers to the registry
	 * 
	 * @param registry
	 * 		  the view controller registry 
	 * 
	 * @return void
	 * 
	 */
    @Override
	public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.addViewController("/newadmin").setViewName("forward:/newadmin/index.html");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/members").setViewName("members");
		registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");

        registry.addRedirectViewController("/admin", "/admin/");
        registry.addViewController("/admin/").setViewName("admin/index");

		registry.addViewController("/status").setViewName("status");
	}

    /**
     * Add resource handlers to the registry
     * For vue front end and future admin panel
     * and separate them from the main static resources
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve static content from the "front" subfolder by default
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/front/");

        // Serve static content from the "admin" subfolder for requests to "/admin"
        registry.addResourceHandler("/newadmin/**")
                .addResourceLocations("classpath:/static/newadmin/");

        // main static resources for spring boot
        registry.addResourceHandler("/main/**")
                .addResourceLocations("classpath:/static/main/");
    }
	
    /**
     * Add intercepters to the registry
     * The admin intercepter is for the admin panel
     * To add more information to the template engine
     * such as version and current page
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminIntercepterHandler()).addPathPatterns("/admin/**");
    }
}
