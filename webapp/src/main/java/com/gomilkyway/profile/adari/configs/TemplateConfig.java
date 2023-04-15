package com.gomilkyway.profile.adari.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@Configuration
public class TemplateConfig {
    
    @Bean
    public LayoutDialect layoutDialect() {
      return new LayoutDialect();
    }

}
