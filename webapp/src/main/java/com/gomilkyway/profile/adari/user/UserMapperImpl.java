package com.gomilkyway.profile.adari.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    
    @Override
    public UserDTO toDto(User user) {
        if ( user == null ) {
            return null;
        }
        
        UserDTO userDTO = new UserDTO();
        
        userDTO.setId( user.getId() );
        userDTO.setName( user.getName() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setRoles( user.getRoles() );

        
        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }
        
        User user = new User();
        
        user.setId( userDTO.getId() );
        user.setName( userDTO.getName() );
        user.setUsername( userDTO.getUsername() );
        user.setPassword( userDTO.getPassword() );
        user.setEmail( userDTO.getEmail() );
        user.setRoles( userDTO.getRoles() );
        
        return user;
    }

    public User toExistingEntity(User user, UserDTO userDTO) {
        if ( userDTO == null || user == null) {
            return null;
        }
        
        if ( userDTO.getId() != null ) {
            user.setId( userDTO.getId() );
        }
        if ( userDTO.getName() != null ) {
            user.setName( userDTO.getName() );
        }
        if ( userDTO.getUsername() != null ) {
            user.setUsername( userDTO.getUsername() );
        }
        if ( userDTO.getPassword() != null ) {
            user.setPassword( userDTO.getPassword() );
        }
        if ( userDTO.getEmail() != null ) {
            user.setEmail( userDTO.getEmail() );
        }
        if ( userDTO.getRoles() != null ) {
            user.setRoles( userDTO.getRoles() );
        }
        
        return user;
    }
}
