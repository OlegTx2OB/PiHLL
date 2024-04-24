package com.example.papadoner.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class OrderDtoTest {

    @Test
    void allArgsConstructor_CorrectConstructorData_OrderDtoTest() {
        // Setup
        long id = 1;
        List<DonerDto> donerDtos = new ArrayList<>();
        Date timestamp = new Date();

        // Act
        OrderDto orderDto = new OrderDto(id, donerDtos, timestamp);

        // Assert
        assertEquals(id, orderDto.getId());
        assertEquals(donerDtos, orderDto.getDonerDtos());
        assertEquals(timestamp, orderDto.getTimestamp());
    }

    @Test
    void gettersSetters_CorrectGettersSetters_OrderDtoTest() {
        // Setup
        OrderDto orderDto = new OrderDto();

        long id = 1;
        List<DonerDto> donerDtos = new ArrayList<>();
        Date timestamp = new Date();

        // Act
        orderDto.setId(id);
        orderDto.setDonerDtos(donerDtos);
        orderDto.setTimestamp(timestamp);

        // Assert
        assertEquals(id, orderDto.getId());
        assertEquals(donerDtos, orderDto.getDonerDtos());
        assertEquals(timestamp, orderDto.getTimestamp());
    }

    @Test
    void noArgsConstructor_DefaultValues_OrderDtoTest() {
        // Act
        OrderDto orderDto = new OrderDto();

        // Assert
        assertEquals(0, orderDto.getId());
        assertNull(orderDto.getTimestamp());
        assertEquals(List.of(), orderDto.getDonerDtos());
    }
}