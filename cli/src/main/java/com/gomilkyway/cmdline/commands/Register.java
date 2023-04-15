package com.gomilkyway.cmdline.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import org.springframework.stereotype.Component;

import picocli.CommandLine.Help;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import jakarta.validation.ValidationException;

import com.gomilkyway.profile.adari.user.UserDTO;
import com.gomilkyway.profile.adari.user.UserRole;
import com.gomilkyway.profile.adari.user.UserService;

@Component
@Command(
    name = "register",
    description = "Register new user",
    mixinStandardHelpOptions = true,
    sortOptions = false
)
public class Register implements Callable<Integer> {

    @Option(names = {"-u", "--username"}, description = "Username", required = true, order = 1)
    private String username;

    @Option(names = {"-e", "--email"}, description = "Email", required = true, order = 3)
    private String email;

    @Option(names = {"-r", "--role"}, description = "User Role", order = 4, defaultValue = "READER", showDefaultValue = Help.Visibility.ALWAYS)
    private UserRole role = null;

    @Option(names = {"-p", "--password"}, description = "Password", required = true, order = 5, arity = "0..1")
    private char[] password = null;

    @Option(names = {"-n", "--name"}, description = "Name", required = false, order = 6)
    private String name = "";

    private UserService userService;

    public Register(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Integer call() throws Exception {
        
        
        if (userService.doesUserExist(username)){
            throw new ValidationException("User already exists");
        }

        System.out.println("Registering new user: ");

        UserDTO user = new UserDTO();
        List<String> errors = null;

        
        String pass = "";
        if (password.length > 0){
            pass = new String(password).trim();
        }

        String confirm = "";
        while (confirm == "") {
            while (pass == "") {
                try {
                    password = System.console().readPassword("Enter password: ");
                    if (password.length > 0) {
                        pass = new String(password).trim();
                        user.setPassword(pass);
                        if ((errors = userService.validateUser(user, "password")) != null) {
                            pass = "";
                            throw new IllegalArgumentException(errors.toString().replaceAll("[\\[\\]]", ""));
                        }
                    } else {
                        throw new IllegalArgumentException("Password cannot be empty");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    password = null;
                }
            }

            int tries = 1;
            while (confirm == "") {
                try {
                    String prompt = "Confirm password" + (tries>1?" (try "+ tries +")":"") + ": ";
                    confirm = new String(System.console().readPassword(prompt)).trim();
                    if (!pass.equals(confirm)) {
                        throw new IllegalArgumentException();
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Passwords do not match");
                    confirm = "";
                    tries++;
                    if (tries > 3) {
                        System.out.println("Too many failures, try entering your password again or Ctrl+C to abort");
                        pass = "";
                        break;
                    }
                }
            }
        }

        while (role == null) {
            try {
                String roleList = Arrays.toString(UserRole.values());
                role = UserRole.valueOf(System.console().readLine("Enter Role ("+roleList+"): ").toUpperCase().trim());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid role");
            }
        }
       
        Set<UserRole> roles;
        if (role != null) {
            roles = Set.of(role);
        } else {
            role = UserRole.READER;
            roles = Set.of(role);
        }

        if (role != UserRole.READER) {
            roles.add(UserRole.READER);
        }

        user = new UserDTO();
        user.setUsername(username);
        user.setPassword(pass);
        user.setConfirmPassword(confirm);
        user.setEmail(email);
        user.setName(name);

        user.setRoles(roles);

        try {
            errors = userService.validateUser(user);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return 1;
        }

        if (errors != null){ 
            for (String error : errors) {
                System.out.println(error);
            }
            return 1;
        }

        
        Arrays.fill(password, ' ');
        confirm = "";

        if (userService.registerNewUser(user) != null) {
            System.out.println("User registered successfully");
        } else {
            System.out.println("User registration failed");
        }

        return 0;
    }
}

