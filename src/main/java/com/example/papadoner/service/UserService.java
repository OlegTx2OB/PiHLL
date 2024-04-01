package com.example.papadoner.service;

import com.example.papadoner.dto.UserDto;
import com.example.papadoner.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    UserDto createUser(User user, Set<Long> orderIds);
    UserDto getUserById(long id);
    UserDto updateUser(long id, User newUser, Set<Long> orderIds);
    void deleteUser(long id);
    List<UserDto> getAllUsers();
    List<UserDto> findUsersWithMoreOrdersThan(int count);
}
