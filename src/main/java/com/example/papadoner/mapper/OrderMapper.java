package com.example.papadoner.mapper;

import com.example.papadoner.dto.OrderDto;
import com.example.papadoner.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    private final DonerMapper mDonerMapper;

    @Autowired
    public OrderMapper(DonerMapper donerMapper) {
        this.mDonerMapper = donerMapper;
    }

    public OrderDto toDto(Order order) {
        return new OrderDto(
                order.getId(),
                mDonerMapper.toDtos(order.getDoners()),
                order.getTimestamp());
    }

    public List<OrderDto> toDtos(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        if (orders != null) {
            for (Order order : orders) {
                orderDtos.add(toDto(order));
            }
        }
        return orderDtos;
    }

}
