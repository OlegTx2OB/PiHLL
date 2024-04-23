package com.example.papadoner.mapper;

import com.example.papadoner.dto.UserDto;
import com.example.papadoner.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getTelephone(),
                OrderMapper.toDtos(user.getOrders()));
    }

    public static List<UserDto> toDtos(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        if (users != null) {
            for (User user : users) {
                userDtos.add(toDto(user));
            }
        }
        return userDtos;
    }
}
