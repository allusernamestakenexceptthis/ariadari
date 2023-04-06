package com.gomilkyway.profile.adari.user;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

   	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Optional<User> emptyOrUser = userRepository.findByUsername(username);

		if (emptyOrUser.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}else{
			User user = emptyOrUser.get();
			return new org.springframework.security.core.userdetails.User(
					user.getUsername(),
					user.getPassword(),
					user.getRoles()
					.stream()
					.map(role -> new org.springframework.security.core.authority.SimpleGrantedAuthority(role.name()))
					.collect(Collectors.toSet())
			);
		}
	}

    public void registerNewUser(User user) {
        userRepository.save(user);
    }

}
