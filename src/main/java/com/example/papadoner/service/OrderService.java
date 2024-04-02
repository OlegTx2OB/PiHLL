package com.example.papadoner.service;

import com.example.papadoner.dto.OrderDto;
import com.example.papadoner.model.Order;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(Order order, Long userId, List<Long> donerIds);

    OrderDto getOrderById(long id);

    OrderDto updateOrder(long id, Order newOrder, Long userId, List<Long> donerIds);

    void deleteOrder(long id);

    List<OrderDto> getAllOrders();
}
