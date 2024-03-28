package com.example.papadoner.service;

import com.example.papadoner.model.Order;

import java.util.List;

public interface OrderService {

    public Order createOrder(Order order);
    public Order getOrderById(long id);
    public Order updateOrder(long id, Order newOrder);
    public void deleteOrder(long id);
    public List<Order> getAllOrders();
}
