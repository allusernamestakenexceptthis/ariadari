package com.gomilkyway.profile.adari.configs;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


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
        registry.addViewController("/admin").setViewName("forward:/admin/index.html");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/members").setViewName("members");
		registry.addViewController("/login").setViewName("login");

		registry.addViewController("/status").setViewName("status");
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve static content from the "front" subfolder by default
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/front/");

        // Serve static content from the "admin" subfolder for requests to "/admin"
        registry.addResourceHandler("/admin/**")
                .addResourceLocations("classpath:/static/admin/");
    }

	
}
