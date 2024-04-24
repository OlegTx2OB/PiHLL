package com.example.papadoner.service.impl;

import com.example.papadoner.dto.OrderDto;
import com.example.papadoner.model.Doner;
import com.example.papadoner.model.Order;
import com.example.papadoner.model.User;
import com.example.papadoner.repository.DonerRepository;
import com.example.papadoner.repository.IngredientRepository;
import com.example.papadoner.repository.OrderRepository;
import com.example.papadoner.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository mOrderRepository;
    @Mock
    private UserRepository mUserRepository;
    @Mock
    private DonerRepository mDonerRepository;
    @InjectMocks
    private OrderServiceImpl mOrderService;

    @Test
    void correctConstructorTest() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        DonerRepository donerRepository = mock(DonerRepository.class);

        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository, userRepository, donerRepository);

        assertEquals(orderRepository, orderService.getMOrderRepository());
        assertEquals(userRepository, orderService.getMUserRepository());
        assertEquals(donerRepository, orderService.getMDonerRepository());
    }

//    @Test
//    void createOrder_WithUserIdAndDonerIds_Success() {
//        // Arrange
//        long userId = 1L;
//        List<Long> donerIds = List.of(1L, 2L);
//        User user = new User(userId, 123, null);
//        when(mUserRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(mDonerRepository.findById(1L)).thenReturn(Optional.of(new Doner()));
//        when(mDonerRepository.findById(2L)).thenReturn(Optional.of(new Doner()));
//        Order order = new Order();
//
//        // Act
//        mOrderService.createOrder(order, userId, donerIds);
//
//        // Assert
//        assertNotNull(order.getUser());
//        assertEquals(2, order.getDoners().size());
//        verify(mOrderRepository, times(1)).save(order);
//    }

//    @Test
//    void createOrder_WithNullUserId_Success() {
//        // Arrange
//        List<Long> donerIds = List.of(1L, 2L);
//        when(mDonerRepository.findById(1L)).thenReturn(Optional.of(new Doner()));
//        when(mDonerRepository.findById(2L)).thenReturn(Optional.of(new Doner()));
//        Order order = new Order();
//
//        // Act
//        mOrderService.createOrder(order, null, donerIds);
//
//        // Assert
//        assertNull(order.getUser());
//        assertEquals(2, order.getDoners().size());
//        verify(mOrderRepository, times(1)).save(order);
//    }

//    @Test
//    void createOrder_WithNullDonerIds_Success() {
//        // Arrange
//        long userId = 1L;
//        User user = new User(userId, 123, null);
//        when(mUserRepository.findById(userId)).thenReturn(Optional.of(user));
//        Order order = new Order();
//
//        // Act
//        mOrderService.createOrder(order, userId, null);
//
//        // Assert
//        assertNotNull(order.getUser());
//        assertTrue(order.getDoners().isEmpty());
//        verify(mOrderRepository, times(1)).save(order);
//    }

    @Test
    void getOrderById_OrderFound_Success() {
        // Arrange
        long orderId = 1L;
        Order order = new Order(orderId, new User(), List.of(new Doner()), null);
        when(mOrderRepository.findById(orderId)).thenReturn(Optional.of(order));

        // Act
        OrderDto result = mOrderService.getOrderById(orderId);

        // Assert
        assertNotNull(result);
        assertEquals(order.getId(), result.getId());
    }

    @Test
    void getOrderById_OrderNotFound_ExceptionThrown() {
        // Arrange
        long nonExistentOrderId = 999L;
        when(mOrderRepository.findById(nonExistentOrderId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> mOrderService.getOrderById(nonExistentOrderId));
    }

    @Test
    void updateOrder_OrderFound_Success() {
        // Arrange
        long orderId = 1L;
        Order oldOrder = new Order(orderId, new User(), List.of(new Doner()), null);
        Order newOrder = new Order(orderId, new User(), List.of(new Doner()), null);
        when(mOrderRepository.findById(orderId)).thenReturn(Optional.of(oldOrder));
        when(mOrderRepository.save(newOrder)).thenReturn(newOrder);

        // Act
        OrderDto result = mOrderService.updateOrder(orderId, newOrder, null, null);

        // Assert
        assertNotNull(result);
        assertEquals(newOrder.getId(), result.getId());
    }

    @Test
    void updateOrder_OrderNotFound_ExceptionThrown() {
        // Arrange
        long nonExistentOrderId = 999L;
        Order newOrder = new Order(nonExistentOrderId, new User(), List.of(new Doner()), null);
        when(mOrderRepository.findById(nonExistentOrderId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> mOrderService.updateOrder(nonExistentOrderId, newOrder, null, null));
    }

//    @Test
//    void deleteOrder_OrderFound_Success() {
//        // Arrange
//        long orderId = 1L;
//        Order order = new Order(orderId, new User(), List.of(new Doner()), null);
//        when(mOrderRepository.findById(orderId)).thenReturn(Optional.of(order));
//
//        // Act
//        mOrderService.deleteOrder(orderId);
//
//        // Assert
//        verify(mOrderRepository, times(1)).deleteById(orderId);
//    }

//    @Test
//    void deleteOrder_OrderNotFound_ExceptionThrown() {
//        // Arrange
//        long nonExistentOrderId = 999L;
//        when(mOrderRepository.findById(nonExistentOrderId)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(EmptyResultDataAccessException.class, () -> mOrderService.deleteOrder(nonExistentOrderId));
//    }

    @Test
    void getAllOrders_Success() {
        // Arrange
        List<Order> orders = List.of(new Order(), new Order());
        when(mOrderRepository.findAll()).thenReturn(orders);

        // Act
        List<OrderDto> result = mOrderService.getAllOrders();

        // Assert
        assertNotNull(result);
        assertEquals(orders.size(), result.size());
    }
}
