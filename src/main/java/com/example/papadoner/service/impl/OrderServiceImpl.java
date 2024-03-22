package com.example.papadoner.service.impl;

import com.example.papadoner.model.Order;
import com.example.papadoner.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    public OrderServiceImpl() {

    }

    @Override
    public Order createOrder(Order order) {
        return null;
    }

    @Override
    public Order getOrderById() {
        return null;
    }

    @Override
    public Order updateOrder(long id, Order updatedOrder) {
        return null;
    }

    @Override
    public void deleteOrder(long id) {

    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }
}
