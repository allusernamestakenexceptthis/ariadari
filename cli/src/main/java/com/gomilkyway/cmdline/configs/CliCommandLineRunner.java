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