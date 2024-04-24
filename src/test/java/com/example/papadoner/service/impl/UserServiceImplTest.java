package com.example.papadoner.service.impl;

import com.example.papadoner.dto.UserDto;
import com.example.papadoner.model.Doner;
import com.example.papadoner.model.Order;
import com.example.papadoner.model.User;
import com.example.papadoner.repository.OrderRepository;
import com.example.papadoner.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository mUserRepository;
    @Mock
    private OrderRepository mOrderRepository;
    @InjectMocks
    private UserServiceImpl mUserService;

    @Test
    void correctConstructorTest() {
        UserRepository userRepository = mock(UserRepository.class);
        OrderRepository orderRepository = mock(OrderRepository.class);

        UserServiceImpl userService = new UserServiceImpl(userRepository, orderRepository);

        assertEquals(userRepository, userService.getMUserRepository());
        assertEquals(orderRepository, userService.getMOrderRepository());
    }

//    @Test
//    void createUser_Success() {
//        // Arrange
//
//        Set<Long> orderIds = Set.of(1L, 2L);
//
//        when(mOrderRepository.findById(1L)).thenReturn(Optional.of(new Order()));
//        when(mOrderRepository.findById(2L)).thenReturn(Optional.of(new Order()));
//        User user = new User();
//
//        // Act
//        mUserService.createUser(user, orderIds);
//
//        // Assert
//        verify(mUserRepository, times(1)).save(user);
//        assertEquals(orderIds, user.getOrders());
//    }

    @Test
    void getUserById_UserFound_Success() {
        // Arrange
        long userId = 1L;
        User user = new User(userId, 123, List.of());
        when(mUserRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        UserDto result = mUserService.getUserById(userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getId());
    }

    @Test
    void getUserById_UserNotFound_ExceptionThrown() {
        // Arrange
        long nonExistentUserId = 999L;
        when(mUserRepository.findById(nonExistentUserId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> mUserService.getUserById(nonExistentUserId));
    }

    @Test
    void updateUser_UserFound_SuccessTest() {
        // Arrange
        long userId = 1L;
        User oldUser = new User(userId, 100, List.of());
        User newUser = new User(userId, 123, List.of());

        when(mUserRepository.findById(userId)).thenReturn(Optional.of(oldUser));
        when(mUserRepository.save(newUser)).thenReturn(newUser);

        // Act
        UserDto result = mUserService.updateUser(userId, newUser, null);

        // Assert
        assertNotNull(result);
        assertEquals(newUser.getId(), result.getId());
        verify(mUserRepository, times(1)).save(newUser);
    }

//    @Test
//    void updateUser_UserNotFound_ExceptionThrown() {
//        // Arrange
//        long nonExistentUserId = 999L;
//        User newUser = new User(nonExistentUserId, 123, List.of());
//        Set<Long> orderIds = Set.of(1L, 2L);
//        when(mUserRepository.findById(nonExistentUserId)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(EntityNotFoundException.class, () -> mUserService.updateUser(nonExistentUserId, newUser, orderIds));
//    }

    @Test
    void deleteUser_Success() {
        // Arrange
        long userId = 1L;

        // Act
        mUserService.deleteUser(userId);

        // Assert
        verify(mUserRepository, times(1)).deleteById(userId);
    }

    @Test
    void getAllUsers_Success() {
        // Arrange
        List<User> users = List.of(new User(), new User());
        when(mUserRepository.findAll()).thenReturn(users);

        // Act
        List<UserDto> result = mUserService.getAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(users.size(), result.size());
    }

    @Test
    void findUsersWithMoreOrdersThan_Success() {
        // Arrange
        int count = 2;
        List<User> users = List.of(new User(), new User());
        when(mUserRepository.findUsersWithMoreOrdersThan(count)).thenReturn(users);

        // Act
        List<UserDto> result = mUserService.findUsersWithMoreOrdersThan(count);

        // Assert
        assertNotNull(result);
        assertEquals(users.size(), result.size());
    }
}
