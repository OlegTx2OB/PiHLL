package com.example.papadoner.mapper;

import com.example.papadoner.dto.OrderDto;
import com.example.papadoner.dto.UserDto;
import com.example.papadoner.model.Order;
import com.example.papadoner.model.User;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void toDtos_CorrectConvertation_UserMapperTest() {
        //Setup
        User user = new User();
        user.setId(1);
        user.setTelephone(1);

        Order order = new Order();
        order.setId(1);
        order.setTimestamp(new Date());

        user.setOrders(List.of(order));

        //Act
        List<UserDto> userDtos = UserMapper.toDtos(List.of(user));

        //Assert
        assertNotNull(userDtos);
        assertEquals(1, userDtos.size());
        UserDto userDto = userDtos.get(0);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getTelephone(), userDto.getTelephone());
        OrderDto orderDto = userDto.getOrderDtos().get(0);
        assertEquals(order.getId(), orderDto.getId());
        assertEquals(order.getTimestamp(), orderDto.getTimestamp());
    }

    @Test
    void toDtos_GetEmptyListIfPassNullArg_UserMapperTest() {
        //Act
        List<UserDto> userDtos = UserMapper.toDtos(null);

        //Assert
        assertNotNull(userDtos);
        assertTrue(userDtos.isEmpty());
    }

    @Test
    void toDtos_GetEmptyListIfPassEmptyList_UserMapperTest() {
        //Act
        List<UserDto> userDtos = UserMapper.toDtos(List.of());

        //Assert
        assertNotNull(userDtos);
        assertTrue(userDtos.isEmpty());
    }

}
