package com.example.papadoner.service;

import com.example.papadoner.model.User;

import java.util.List;

public interface UserService {

    public User createUser(User user);
    public User getUserById(long id);
    public User updateUser(long id, User newUser);
    public void deleteUser(long id);
    public List<User> getAllUsers();
    public List<User> findUsersWithMoreOrdersThan(int count);
}
