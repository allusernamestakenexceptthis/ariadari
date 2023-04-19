/*
 * Copyright 2023 Ari Adari
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gomilkyway.cmdline.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import jakarta.validation.ValidationException;

import com.gomilkyway.profile.adari.user.User;
import com.gomilkyway.profile.adari.user.UserDTO;
import com.gomilkyway.profile.adari.user.UserRole;
import com.gomilkyway.profile.adari.user.UserService;

@Component
@Command(
    name = "update",
    description = "Update existing user",
    mixinStandardHelpOptions = true,
    sortOptions = false
)
public class Update implements Callable<Integer> {

    @Option(names = {"-t", "--target_username"}, description = "The username of the user you want to update", required = true, order = 1)
    private String targetUsername;

    @Option(names = {"-u", "--username"}, description = "Username", required = false, order = 2)
    private String username = null;

    @Option(names = {"-e", "--email"}, description = "Email", required = false, order = 3)
    private String email = null;

    @Option(names = {"-ar", "--add-role"}, description = "Add user Role", order = 4)
    private UserRole addRole = null;

    @Option(names = {"-rr", "--remove-role"}, description = "Remove user Role", order = 5)
    private UserRole removeRole = null;

    @Option(names = {"-p", "--password"}, description = "Password", required = false, order = 6, arity = "0..1")
    private char[] password = null;

    @Option(names = {"-n", "--name"}, description = "Name", required = false, order = 7)
    private String name = null;

    private UserService userService;

    public Update(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Integer call() throws Exception {

        if (username == null && email == null && addRole == null && removeRole == null && password == null && name == null) {
            throw new IllegalArgumentException("No parameters specified.");
        }

        if (!userService.doesUserExist(targetUsername)){
            throw new ValidationException("User doesn't exist");
        }

        System.out.println("Updating existing user: ");
        System.out.println("Username: " + targetUsername);
        User user;
        try {
            user = userService.findByUsername(targetUsername);
        } catch (UsernameNotFoundException e) {
            throw new ValidationException("User doesn't exist");
        }

        List<String> errors = null;
        UserDTO userDTO = userService.getDTOFromUser(user);
        
        if (password != null){
            
            String pass = "";
            if (password.length > 0){
                pass = new String(password).trim();
                userDTO.setPassword(pass);
            }
            String confirm = "";

            while (confirm == "") {
                while (pass == "") {
                    try {
                        password = System.console().readPassword("Enter password: ");
                        if (password.length > 0) {
                            pass = new String(password).trim();
                            userDTO.setPassword(pass);
                            if ((errors = userService.validateUser(userDTO, "password")) != null) {
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
                        userDTO.setConfirmPassword(confirm);
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
        }

        
       
        Set<UserRole> roles = user.getRoles();

        if (removeRole != null && roles.contains(removeRole)) {
            roles.remove(removeRole);
        }

        if (addRole != null && !roles.contains(addRole)) {
            roles.add(addRole);
        }

        

        if (username != null) {
            userDTO.setUsername(username);
        }
        if (email != null) {
            userDTO.setEmail(email);
        }
        
        if (name != null) {
            userDTO.setName(name);
        }

        if (removeRole != null || addRole != null) {
            userDTO.setRoles(roles);
        }

        try {
            errors = userService.validateUser(userDTO);
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

        if (password != null) {
            Arrays.fill(password, ' ');
        }

        if (userService.updateUser(user, userDTO) != null) {
            System.out.println("User updated successfully");
        } else {
            System.out.println("User update failed");
        }

        return 0;
    }
}

