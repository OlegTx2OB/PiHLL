package com.example.papadoner.mapper;

import com.example.papadoner.dto.OrderDto;
import com.example.papadoner.model.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    public static OrderDto toDto(Order order) {
        return new OrderDto(
                order.getId(),
                DonerMapper.toDtos(order.getDoners()),
                order.getTimestamp());
    }

    public static List<OrderDto> toDtos(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        if (orders != null) {
            for (Order order : orders) {
                orderDtos.add(toDto(order));
            }
        }
        return orderDtos;
    }

}
