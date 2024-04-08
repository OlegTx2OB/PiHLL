package com.example.papadoner.controller;

import com.example.papadoner.dto.OrderDto;
import com.example.papadoner.model.Order;
import com.example.papadoner.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
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
        log.info("POST endpoint createOrder [class OrderController] was called");
        mOrderService.createOrder(order, userId, donerIds);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") long id) {
        log.info("GET endpoint getOrderById [class OrderController] was called");
        OrderDto order = mOrderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("id") long id,
                                             @RequestBody Order updatedOrder,
                                             @RequestParam(required = false) Long userId,
                                             @RequestParam(required = false) List<Long> donerIds) {
        log.info("PUT endpoint updateOrder [class OrderController] was called");
        OrderDto order = mOrderService.updateOrder(id, updatedOrder, userId, donerIds);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") long id) {
        log.info("DELETE endpoint deleteOrder [class OrderController] was called");
        mOrderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        log.info("GET endpoint getAllOrders [class OrderController] was called");
        List<OrderDto> orders = mOrderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
