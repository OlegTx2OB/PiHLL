package com.example.papadoner.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class UserDtoTest {

    @Test
    void allArgsConstructor_CorrectConstructorData_UserDtoTest() {
        // Setup
        long id = 1;
        long telephone = 1234567890;
        List<OrderDto> orderDtos = new ArrayList<>();

        // Act
        UserDto userDto = new UserDto(id, telephone, orderDtos);

        // Assert
        assertEquals(id, userDto.getId());
        assertEquals(telephone, userDto.getTelephone());
        assertEquals(orderDtos, userDto.getOrderDtos());
    }

    @Test
    void gettersSetters_CorrectGettersSetters_UserDtoTest() {
        // Setup
        UserDto userDto = new UserDto();

        long id = 1;
        long telephone = 1234567890;
        List<OrderDto> orderDtos = new ArrayList<>();

        // Act
        userDto.setId(id);
        userDto.setTelephone(telephone);
        userDto.setOrderDtos(orderDtos);

        // Assert
        assertEquals(id, userDto.getId());
        assertEquals(telephone, userDto.getTelephone());
        assertEquals(orderDtos, userDto.getOrderDtos());
    }

    @Test
    void noArgsConstructor_DefaultValues_UserDtoTest() {
        // Act
        UserDto userDto = new UserDto();

        // Assert
        assertEquals(0, userDto.getId());
        assertEquals(0, userDto.getTelephone());
        assertEquals(List.of(), userDto.getOrderDtos());
    }
}
