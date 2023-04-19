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

package com.gomilkyway.profile.adari.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gomilkyway.profile.adari.user.User.LimitedUserInfo;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @PostMapping(path = "/register")
    public String registerNewUser (@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model) {
        
        if (user.getUsername()!=null && userService.doesUserExist(user.getUsername())) {
            bindingResult.rejectValue("username", "", messageSource.getMessage("user.username_exists", null, LocaleContextHolder.getLocale()));
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        UserDTO userResponse = userService.registerNewUser(user);;
        if (userResponse == null) {
            bindingResult.addError(new ObjectError("globalError", messageSource.getMessage("user.error_occured_registering_user", null, LocaleContextHolder.getLocale())));
            return "register";
        }

        return "redirect:login?registered";
    }

    @GetMapping(path = "/register")
    public String registerNewUser (Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }

	@GetMapping(path = "/allusers")
	public @ResponseBody Iterable<LimitedUserInfo> getAllUsers() {
		//returns json or xml users
		return userRepository.findAllBy(LimitedUserInfo.class);
	}

}
