package com.gomilkyway.profile.adari.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;


@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

    @Autowired
    private ValidatorFactory validatorFactory;

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

    public Set<ConstraintViolation<UserDTO>> getUserValidations(UserDTO user) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        return violations;
    }

    public List<String> validateUser(UserDTO user) {
        Set<ConstraintViolation<UserDTO>> violations = getUserValidations(user);
        List<String> errors = new ArrayList<>();
        if (!violations.isEmpty()) {
            System.out.println("Validation errors:");
            violations.forEach(violation -> errors.add(violation.getMessage()));
            ;
        }
        if (errors.isEmpty()) {
            return null;
        }
        return errors;
    }

    public void registerNewUser(User user) {
        userRepository.save(user);
    }

}
