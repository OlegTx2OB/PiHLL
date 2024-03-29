package com.example.papadoner.controller;

import com.example.papadoner.model.Doner;
import com.example.papadoner.model.Order;
import com.example.papadoner.service.DonerService;
import com.example.papadoner.service.OrderService;
import com.example.papadoner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final DonerService donerService;

    @Autowired
    public OrderController(OrderService orderService,
                           UserService userService,
                           DonerService donerService) {
        this.orderService = orderService;
        this.donerService = donerService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order,
                                             @RequestParam List<Long> donerIds,
                                             @RequestParam Long userId) {
        order.setUser(userService.getUserById(userId));

        List<Doner> donerList = new ArrayList<>();
        for (Long id : donerIds) {
            donerList.add(donerService.getDonerById(id));
        }
        order.setDoners(donerList);

        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") short id) {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") short id, @RequestBody Order updatedOrder) {
        Order order = orderService.updateOrder(id, updatedOrder);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") short id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
