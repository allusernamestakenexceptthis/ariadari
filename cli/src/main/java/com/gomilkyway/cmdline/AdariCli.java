package com.gomilkyway.cmdline;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.core.io.ClassPathResource;

import com.gomilkyway.cmdline.configs.CliCOnfig;
import com.gomilkyway.cmdline.utils.CliVersionUtil;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "adari-cli", description = "Ari Adari Cli Application")
public class AdariCli {

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Display this help message")
    private boolean helpRequested;

    public static void main(String[] args) {
        System.out.println("Ari Adari CLI Version " + CliVersionUtil.getVersion());
        SpringApplication app = new SpringApplication(CliCOnfig.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        try {
            app.setDefaultProperties(loadProperties("application.properties", "application-cli.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        app.run(args);
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

}