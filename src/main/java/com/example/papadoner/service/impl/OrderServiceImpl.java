package com.example.papadoner.service.impl;

import com.example.papadoner.model.Order;
import com.example.papadoner.repository.OrderRepository;
import com.example.papadoner.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found"));
    }

    @Override
    public Order updateOrder(long id, Order newOrder) {
        if (newOrder == null) {
            throw new IllegalArgumentException("fun updateOrder cannot get null argument");
        }
        Optional<Order> optionalOldOrder = orderRepository.findById(id);
        if (optionalOldOrder.isPresent()) {
            Order oldOrder = optionalOldOrder.get();
            newOrder.setId(oldOrder.getId());
            return orderRepository.save(newOrder);
        } else {
            throw new EntityNotFoundException("Order with id " + id + " not found");
        }
    }

    @Override
    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
