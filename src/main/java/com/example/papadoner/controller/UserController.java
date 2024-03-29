package com.example.papadoner.controller;

import com.example.papadoner.model.Order;
import com.example.papadoner.model.User;
import com.example.papadoner.service.OrderService;
import com.example.papadoner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public UserController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user, @RequestParam Set<Long> orderIds) {
        Set<Order> orderSet = new HashSet<>();
        for (Long id : orderIds) {
            orderSet.add(orderService.getOrderById(id));
        }
        user.setOrders(orderSet);

        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/count/{count}")
    public ResponseEntity<List<User>> findUsersWithMoreOrdersThan(@PathVariable("count") int count) {
        List<User> users = userService.findUsersWithMoreOrdersThan(count);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}