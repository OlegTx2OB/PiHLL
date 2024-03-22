package com.example.papadoner.service.impl;

import com.example.papadoner.model.User;
import com.example.papadoner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserServiceImpl() {

    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public User updateUser(long id, User updatedUser) {
        return null;
    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
