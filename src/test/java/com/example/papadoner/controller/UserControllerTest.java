package com.example.papadoner.controller;

import com.example.papadoner.dto.UserDto;
import com.example.papadoner.model.User;
import com.example.papadoner.service.OrderService;
import com.example.papadoner.service.UserService;
import com.example.papadoner.service.impl.OrderServiceImpl;
import com.example.papadoner.service.impl.UserServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService mUserService;

    @InjectMocks
    private UserController mUserController;

    @Test
    void createUserTest() {
        // Setup
        User user = new User();
        Set<Long> orderIds = Set.of(1L, 2L);

        // Act
        ResponseEntity<Void> response = mUserController.createUser(user, orderIds);

        // Assert
        verify(mUserService).createUser(user, orderIds);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getUserByIdTest() {
        // Setup
        long userId = 1L;
        UserDto expectedUserDto = new UserDto(userId, 123, List.of());

        when(mUserService.getUserById(userId)).thenReturn(expectedUserDto);

        // Act
        ResponseEntity<UserDto> response = mUserController.getUserById(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedUserDto, response.getBody());
    }

    @Test
    void updateUserTest() {
        // Setup
        long userId = 1L;
        User updatedUser = new User();
        Set<Long> orderIds = Set.of(1L, 2L);
        UserDto expectedUserDto = new UserDto(userId, 123, List.of());

        when(mUserService.updateUser(userId, updatedUser, orderIds)).thenReturn(expectedUserDto);

        // Act
        ResponseEntity<UserDto> response = mUserController.updateUser(userId, updatedUser, orderIds);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedUserDto, response.getBody());
    }

    @Test
    void deleteUserTest() {
        // Setup
        long userId = 1L;

        // Act
        ResponseEntity<Void> response = mUserController.deleteUser(userId);

        // Assert
        verify(mUserService).deleteUser(userId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getAllUsersTest() {
        // Setup
        List<UserDto> expectedUserDtos = List.of(
                new UserDto(1L, 123, List.of()),
                new UserDto(2L, 123, List.of()));

        when(mUserService.getAllUsers()).thenReturn(expectedUserDtos);

        // Act
        ResponseEntity<List<UserDto>> response = mUserController.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedUserDtos.size(), response.getBody().size());
        assertEquals(expectedUserDtos, response.getBody());
    }

    @Test
    void findUsersWithMoreOrdersThanTest() {
        // Setup
        int count = 2;
        List<UserDto> expectedUserDtos = List.of(
                new UserDto(1L, 123, List.of()),
                new UserDto(2L, 123, List.of()));

        when(mUserService.findUsersWithMoreOrdersThan(count)).thenReturn(expectedUserDtos);

        // Act
        ResponseEntity<List<UserDto>> response = mUserController.findUsersWithMoreOrdersThan(count);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedUserDtos.size(), response.getBody().size());
        assertEquals(expectedUserDtos, response.getBody());
    }

    @Test
    void correctConstructorTest() {
        UserService orderServiceMock = mock(UserServiceImpl.class);

        UserController orderController = new UserController(orderServiceMock);

        assertEquals(orderServiceMock, orderController.getMUserService());
    }
}
