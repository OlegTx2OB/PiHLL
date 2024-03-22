package com.example.papadoner.mapper;

import com.example.papadoner.dto.UserDto;
import com.example.papadoner.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public UserDto toDto(User user);
}
