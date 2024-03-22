package com.example.papadoner.mapper;

import com.example.papadoner.dto.OrderDto;
import com.example.papadoner.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    public OrderDto toDto(Order order);
}
