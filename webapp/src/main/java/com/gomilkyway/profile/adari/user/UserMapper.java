package com.gomilkyway.profile.adari.user;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper{
    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);
}
