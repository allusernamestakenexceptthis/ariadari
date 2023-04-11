package com.gomilkyway.cmdline;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.core.io.ClassPathResource;

import com.gomilkyway.cmdline.commands.Register;
import com.gomilkyway.cmdline.commands.Cli;
import com.gomilkyway.cmdline.utils.CliVersionUtil;

import com.gomilkyway.profile.adari.configs.WebSecurityConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.IFactory;
import picocli.CommandLine.Option;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gomilkyway.profile.adari.user.UserService;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gomilkyway.cmdline", "com.gomilkyway.profile.adari.user"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebSecurityConfig.class))
@EntityScan(basePackages = {"com.gomilkyway.cmdline", "com.gomilkyway.profile.adari.user"})
@EnableJpaRepositories(basePackages = {"com.gomilkyway.cmdline", "com.gomilkyway.profile.adari.user"})
//@Command(name = "adari-cli", description = "Ari Adari Cli Application", mixinStandardHelpOptions = true)
public class AdariCli implements CommandLineRunner, ExitCodeGenerator {

    private final IFactory factory;
    private final Cli cliCommand;
    private int exitCode;

    public AdariCli(IFactory factory, Cli cliCommand) {
        this.factory = factory;
        this.cliCommand = cliCommand;
    }

    @Override
    public void run(String... args) {
        // let picocli parse command line args and run the business logic
        exitCode = new CommandLine(cliCommand, factory)  
                      .addSubcommand("register", new Register()) 
                      .execute(args);
    }

    public static void main(String[] args) {
        System.out.println("Ari Adari CLI Version " + CliVersionUtil.getVersion());
        //System.exit(SpringApplication.exit(SpringApplication.run(AdariCli.class, args)));
        
        SpringApplication app = new SpringApplication(AdariCli.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        try {
            app.setDefaultProperties(loadProperties("application.properties", "application-cli.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(SpringApplication.exit(app.run(args))); 
        
        /*
        SpringApplication app = new SpringApplication(CliConfig.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        try {
            app.setDefaultProperties(loadProperties("application.properties", "application-cli.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        app.run(args);*/
	}

    private static Properties loadProperties(String... resourceNames) throws IOException {
        Properties properties = new Properties();
        for (String resourceName : resourceNames) {
            try (InputStream inputStream = new ClassPathResource(resourceName).getInputStream()) {
                properties.load(inputStream);
            }
        }
        return properties;
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}