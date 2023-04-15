package com.gomilkyway.profile.adari.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gomilkyway.profile.adari.user.User.LimitedUserInfo;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private UserMapperImpl userMapper;

    @PostMapping(path = "/register")
    public ResponseEntity<UserDTO> registerNewUser (@Valid @RequestBody UserDTO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }

        User userEntity = userMapper.toEntity(user);
        userService.registerNewUser(userEntity);
        UserDTO userResponse = userMapper.toDto(userEntity);


        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping(path = "/register")
    public String registerNewUser (Model model) {
        model.addAttribute("user", new UserDTO());
        try {
            UserDetails user = userService.loadUserByUsername("admin");
            System.out.println(user.getUsername());
        } catch (UsernameNotFoundException e) {
            System.out.println("User not found");
        }
        return "register";
    }

    /*TODO: remove this method
	@PostMapping(path = "/adduser")
	public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email, @RequestParam String password) {
		User n = new User();
		n.setName(name);
		n.setEmail(email);
		n.setPassword(password);
		userRepository.save(n);
		return "Saved";
	}*/

	@GetMapping(path = "/allusers")
	public @ResponseBody Iterable<LimitedUserInfo> getAllUsers() {
		//returns json or xml users
		return userRepository.findAllBy(LimitedUserInfo.class);
	}


}
