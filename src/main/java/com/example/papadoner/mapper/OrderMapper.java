package com.example.papadoner.mapper;

import com.example.papadoner.dto.OrderDto;
import com.example.papadoner.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class OrderMapper {

    private final DonerMapper donerMapper;

    @Autowired
    public OrderMapper(DonerMapper donerMapper) {
        this.donerMapper = donerMapper;
    }

    public OrderDto toDto(Order order) {
        return new OrderDto(
                order.getId(),
                donerMapper.toDtos(order.getDoners()),
                order.getTimestamp());
    }

    public Set<OrderDto> toDtos(Set<Order> orders) {
        Set<OrderDto> orderDtos = new HashSet<>();
        for (Order order : orders) {
            orderDtos.add(toDto(order));
        }
        return orderDtos;
    }
}
