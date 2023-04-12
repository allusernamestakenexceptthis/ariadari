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
}
