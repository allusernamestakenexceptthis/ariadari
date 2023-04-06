package com.gomilkyway.cmdline;

import com.gomilkyway.profile.adari.configs.WebSecurityConfig;
import com.gomilkyway.profile.adari.user.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gomilkyway.cmdline", "com.gomilkyway.profile.adari.user"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebSecurityConfig.class))
@EntityScan(basePackages = {"com.gomilkyway.profile.adari.user"})
@EnableJpaRepositories(basePackages = {"com.gomilkyway.profile.adari.user"})
public class CliCOnfig {
    @Bean
    public CliCommandLineRunner commandLineRunner(UserService userService) {
        return new CliCommandLineRunner(userService);
    }
}
