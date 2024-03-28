package com.example.papadoner.service.impl;

import com.example.papadoner.model.User;
import com.example.papadoner.repository.UserRepository;
import com.example.papadoner.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public User updateUser(long id, User newUser) {
        if (newUser == null) {
            throw new IllegalArgumentException("fun updateUser cannot get null argument");
        }
        Optional<User> optionalOldUser = userRepository.findById(id);
        if (optionalOldUser.isPresent()) {
            User oldUser = optionalOldUser.get();
            newUser.setId(oldUser.getId());
            return userRepository.save(newUser);
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findUsersWithMoreOrdersThan(int count) {
        return userRepository.findUsersWithMoreOrdersThan(count);
    }
}
