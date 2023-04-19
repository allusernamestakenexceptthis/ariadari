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
