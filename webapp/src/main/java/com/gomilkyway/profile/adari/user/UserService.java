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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserMapperImpl userMapper;

    /**
     * Loads a user from the database
     * 
     * @param username
     * @return UserDetails object
     * @throws UsernameNotFoundException
     */
   	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try {
		    User user = findByUsername(username);
			return new CustomUserDetailsUser(
					user.getUsername(),
					user.getPassword(),
					user.getRoles()
					    .stream()
					    .map(
                            role -> new org.springframework.security.core.authority.SimpleGrantedAuthority(role.name())
                        )
					    .collect(Collectors.toSet()),
                    user.getName(),
                    user.getEmail()
			);
		} catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User not found");
        }
	}

    /**
     * Find a user by username
     * 
     * @param username
     * @return User object
     * @throws UsernameNotFoundException
     */
    public User findByUsername(String username) throws UsernameNotFoundException {
        Optional<User> emptyOrUser = userRepository.findByUsername(username);

		if (emptyOrUser.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
        }
        return emptyOrUser.get();
    }

    /**
     * Checks if a user exists in the database
     * 
     * @param username
     * @return boolean
     */
    public boolean doesUserExist(String username) {
        try {
            findByUsername(username);
            return true;
        } catch (UsernameNotFoundException e) {
            return false;
        }
    }

    /**
     * Validates a user
     * 
     * @param user
     * @return Set of ConstraintViolations
     * @throws UsernameNotFoundException
     */
    public Set<ConstraintViolation<UserDTO>> getUserValidations(UserDTO user) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        return violations;
    }

    /**
     * Validates a user property
     * 
     * @param user
     * @param property
     * @return Set of ConstraintViolations
     * @throws UsernameNotFoundException
     */
    public Set<ConstraintViolation<UserDTO>> getUserPropertyValidation(UserDTO user, String property) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(user, property);
        return violations;
    }

    /**
     * Validates a user
     * 
     * @param user
     * @return List of errors
     * @throws UsernameNotFoundException
     */
    public List<String> validateUser(UserDTO user) {
        return validateUser(user, null);
    }

    /**
     * Validates a user property
     * 
     * @param user
     * @param property
     * @return List of errors
     * @throws UsernameNotFoundException
     */
    public List<String> validateUser(UserDTO user, String property) {

        Set<ConstraintViolation<UserDTO>> violations = null;
        if (property != null) {
            violations = getUserPropertyValidation(user, property);
        } else { 
            violations = getUserValidations(user);
        }

        List<String> errors = new ArrayList<>();
        if (!violations.isEmpty()) {
            violations.forEach(violation -> errors.add(violation.getMessage()));
        }
        if (errors.isEmpty()) {
            return null;
        }
        return errors;
    }

    /**
     * Converts a User entity to a UserDTO
     * 
     * @param user
     * @return UserDTO
     */
    public UserDTO getDTOFromUser(User user) {
        return userMapper.toDto(user);
    }

    /**
     * Converts a UserDTO to a User entity
     * 
     * @param userDTO
     * @return User
     */
    public User getUserFromDTO(UserDTO userDTO) {
        return userMapper.toEntity(userDTO);
    }

    /**
     * Registers a new user using a UserDTO
     * 
     * @param user
     * @return UserDTO
     */
    public UserDTO registerNewUser(UserDTO user) {
        User userEntity = getUserFromDTO(user);
        registerNewUser(userEntity);
        return getDTOFromUser(userEntity);
    }

    /**
     * Registers a new user using a User entity
     * 
     * @param user
     */
    public void registerNewUser(User user) {
        userRepository.save(user);
    }

    /**
     * Updates a user using a UserDTO
     * 
     * @param user
     * @param userDTO
     * @return UserDTO
     */
    public UserDTO updateUser(User user, UserDTO userDTO) {
        user = userMapper.toExistingEntity(user, userDTO);
        userRepository.save(user);
        return getDTOFromUser(user);
    }

}
