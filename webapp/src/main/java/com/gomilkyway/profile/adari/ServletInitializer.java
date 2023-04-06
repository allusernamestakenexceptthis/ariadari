package com.gomilkyway.profile.adari;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {


	/**
	 * Configure the application
	 * 
	 * @param application
	 * 		  the application builder
	 * 
	 * @return SpringApplicationBuilder
	 * 
	 */

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AdariApplication.class);
	}

}
