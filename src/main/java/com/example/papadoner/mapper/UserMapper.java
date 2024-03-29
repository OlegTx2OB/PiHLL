package com.example.papadoner.mapper;

import com.example.papadoner.dto.UserDto;
import com.example.papadoner.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    private final OrderMapper orderMapper;

    @Autowired
    public UserMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getTelephone(),
                orderMapper.toDtos(user.getOrders()));
    }

    public List<UserDto> toDtos(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(toDto(user));
        }
        return userDtos;
    }
}
