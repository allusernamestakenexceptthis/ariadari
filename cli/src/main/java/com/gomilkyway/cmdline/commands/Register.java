package com.gomilkyway.cmdline.commands;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import com.gomilkyway.profile.adari.utils.PasswordUtil;
import com.gomilkyway.profile.adari.user.UserDTO;
import com.gomilkyway.profile.adari.user.UserRole;
import com.gomilkyway.profile.adari.user.UserService;

@Component
@Command(
    name = "register",
    description = "Register new user",
    mixinStandardHelpOptions = true,
    version = "1.0.0"
)
public class Register implements Callable<Integer> {

    @Option(names = {"-u", "--username"}, description = "Username", required = true)
    private String username;

    @Option(names = {"-p", "--password"}, description = "Password", required = true, interactive = true)
    private char[] password;

    @Option(names = {"-r", "--role"}, description = "User Role")
    private UserRole role = null;

    @Autowired
    private UserService userService;

    @Override
    public Integer call() throws Exception {

        while (role == null) {
            try {
                String roleList = Arrays.toString(UserRole.values());
                role = UserRole.valueOf(System.console().readLine("Enter Role ("+roleList+"): ").toUpperCase().trim());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid role");
            }
        }
        

        if (role == null) {
            role = UserRole.READER;
        }


        byte[] bytes = new byte[password.length];
        for (int i = 0; i < bytes.length; i++) { bytes[i] = (byte) password[i]; }
        String pass = Arrays.toString(bytes);

        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setPassword(pass);
        user.setRole(role);
        

        List<String> errors = userService.validateUser(user);

        ValidPasswordValidator validator = new ValidPasswordValidator();
        if (!validator.isValid(pass, null)) {
            System.out.println("Invalid password");
            return -1;
        }

        String hash = PasswordUtil.encrypt(pass);
        

        System.out.printf("Hi %s, your password is hashed to %s.%n", username, hash);

        // null out the arrays when done
        Arrays.fill(bytes, (byte) 0);
        Arrays.fill(password, ' ');

        return 0;
    }

    
}
