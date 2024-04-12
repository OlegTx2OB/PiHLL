package com.example.papadoner.controller;

import com.example.papadoner.dto.OrderDto;
import com.example.papadoner.model.Order;
import com.example.papadoner.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService mOrderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.mOrderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody Order order,
                                            @RequestParam(required = false) Long userId,
                                            @RequestParam(required = false) List<Long> donerIds) {
        mOrderService.createOrder(order, userId, donerIds);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") long id) {
        OrderDto order = mOrderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("id") long id,
                                                @RequestBody Order updatedOrder,
                                                @RequestParam(required = false) Long userId,
                                                @RequestParam(required = false) List<Long> donerIds) {
        OrderDto order = mOrderService.updateOrder(id, updatedOrder, userId, donerIds);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") long id) {
        mOrderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = mOrderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
