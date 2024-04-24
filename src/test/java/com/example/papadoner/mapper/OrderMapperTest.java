package com.example.papadoner.mapper;

import com.example.papadoner.dto.DonerDto;
import com.example.papadoner.dto.OrderDto;
import com.example.papadoner.model.Doner;
import com.example.papadoner.model.Order;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class OrderMapperTest {

    @Test
    void toDtos_CorrectConvertation_OrderMapperTest() {
        //Setup
        Order order = new Order();
        order.setId(1);
        order.setTimestamp(new Date());

        Doner doner = new Doner();
        doner.setId(1);
        doner.setName("name");

        order.setDoners(List.of(doner));

        //Act
        List<OrderDto> orderDtos = OrderMapper.toDtos(List.of(order));

        //Assert
        assertNotNull(orderDtos);
        assertEquals(1, orderDtos.size());
        OrderDto orderDto = orderDtos.get(0);
        DonerDto donerDto = orderDto.getDonerDtos().get(0);
        assertEquals(order.getId(), orderDto.getId());
        assertEquals(order.getTimestamp(), orderDto.getTimestamp());
        assertEquals(doner.getId(), donerDto.getId());
        assertEquals(doner.getName(), donerDto.getName());
    }

    @Test
    void toDtos_GetEmptyListIfPassNullArg_OrderMapperTest() {
        //Act
        List<OrderDto> orderDtos = OrderMapper.toDtos(null);

        //Assert
        assertNotNull(orderDtos);
        assertTrue(orderDtos.isEmpty());
    }

    @Test
    void toDtos_GetEmptyListIfPassEmptyList_OrderMapperTest() {
        //Act
        List<OrderDto> orderDtos = OrderMapper.toDtos(List.of());

        //Assert
        assertNotNull(orderDtos);
        assertTrue(orderDtos.isEmpty());
    }
}
