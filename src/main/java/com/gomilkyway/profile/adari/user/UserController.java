package com.gomilkyway.profile.adari.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gomilkyway.profile.adari.user.User.LimitedUserInfo;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping(path = "/adduser")
	public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email, @RequestParam String password) {
		User n = new User();
		n.setName(name);
		n.setEmail(email);
		n.setPassword(password);
		userRepository.save(n);
		return "Saved";
	}

	@GetMapping(path = "/allusers")
	public @ResponseBody Iterable<LimitedUserInfo> getAllUsers() {
		//returns json or xml users
		return userRepository.findAllBy(LimitedUserInfo.class);
	}

}
