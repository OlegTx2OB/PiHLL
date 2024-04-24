package com.example.papadoner.service.impl;

import com.example.papadoner.dto.OrderDto;
import com.example.papadoner.mapper.OrderMapper;
import com.example.papadoner.model.Doner;
import com.example.papadoner.model.Order;
import com.example.papadoner.repository.DonerRepository;
import com.example.papadoner.repository.OrderRepository;
import com.example.papadoner.repository.UserRepository;
import com.example.papadoner.service.OrderService;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Log4j2
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository mOrderRepository;
    private final UserRepository mUserRepository;
    private final DonerRepository mDonerRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            UserRepository userRepository,
                            DonerRepository donerRepository) {
        this.mOrderRepository = orderRepository;
        this.mUserRepository = userRepository;
        this.mDonerRepository = donerRepository;
    }

    @Override
    public void createOrder(Order order, @Nullable Long userId, @Nullable List<Long> donerIds) {
        if (userId != null) {
            order.setUser(mUserRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found")));
        }
        order = setDoners(order, donerIds);
        OrderMapper.toDto(mOrderRepository.save(order));
    }

    @Override
    public OrderDto getOrderById(long id) {
        return OrderMapper.toDto(mOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found")));
    }

    @Override
    public OrderDto updateOrder(long id, Order newOrder, @Nullable Long userId, @Nullable List<Long> donerIds) {
        if (userId != null) {
            newOrder.setUser(mUserRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found")));
        }
        newOrder = setDoners(newOrder, donerIds);

        Order oldOrder = mOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found"));
        newOrder.setId(oldOrder.getId());
        return OrderMapper.toDto(mOrderRepository.save(newOrder));
    }

    @Override
    public void deleteOrder(long id) {
        mOrderRepository.deleteById(id);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return OrderMapper.toDtos(mOrderRepository.findAll());
    }

    private Order setDoners(Order order, List<Long> donerIds) {
        if (donerIds != null) {
            List<Doner> doners = new ArrayList<>();
            for (long id : donerIds) {
                doners.add(
                        mDonerRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(
                                        "Doner with id " + id + " not found")));
            }
            order.setDoners(doners);
        }
        return order;
    }
}
