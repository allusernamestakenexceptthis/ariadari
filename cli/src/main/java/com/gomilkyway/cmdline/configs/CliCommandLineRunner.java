package com.gomilkyway.cmdline.configs;


import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gomilkyway.profile.adari.user.UserService;

public class CliCommandLineRunner implements CommandLineRunner {

    private final UserService userService;

    public CliCommandLineRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Your CLI-specific code here
        System.out.println("Hello World");
        try {
            UserDetails user = userService.loadUserByUsername("admin");
            System.out.println(user.getUsername());
        } catch (UsernameNotFoundException e) {
            System.out.println("User not found");
        }
    }
}