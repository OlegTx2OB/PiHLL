package com.example.papadoner.controller;

import com.example.papadoner.dto.UserDto;
import com.example.papadoner.model.User;
import com.example.papadoner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService mUserService;

    @Autowired
    public UserController(UserService userService) {
        this.mUserService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user,
                                           @RequestParam(required = false) Set<Long> orderIds) {
        mUserService.createUser(user, orderIds);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        UserDto user = mUserService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id,
                                           @RequestBody User updatedUser,
                                           @RequestParam(required = false) Set<Long> orderIds) {
        UserDto user = mUserService.updateUser(id, updatedUser, orderIds);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        mUserService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = mUserService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/count/{count}")
    public ResponseEntity<List<UserDto>> findUsersWithMoreOrdersThan(@PathVariable("count") int count) {
        List<UserDto> users = mUserService.findUsersWithMoreOrdersThan(count);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}