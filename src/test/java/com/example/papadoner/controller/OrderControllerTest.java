package com.example.papadoner.controller;

import com.example.papadoner.dto.OrderDto;
import com.example.papadoner.model.Order;
import com.example.papadoner.service.OrderService;
import com.example.papadoner.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderService mOrderService;

    @InjectMocks
    private OrderController mOrderController;

    @Test
    void createOrderTest() {
        // Setup
        Order order = new Order();
        Long userId = 1L;
        List<Long> donerIds = List.of(1L, 2L);

        // Act
        ResponseEntity<Void> response = mOrderController.createOrder(order, userId, donerIds);

        // Assert
        verify(mOrderService).createOrder(order, userId, donerIds);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getOrderByIdTest() {
        // Setup
        long orderId = 1L;
        OrderDto expectedOrderDto = new OrderDto(orderId, List.of(), null);

        when(mOrderService.getOrderById(orderId)).thenReturn(expectedOrderDto);

        // Act
        ResponseEntity<OrderDto> response = mOrderController.getOrderById(orderId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedOrderDto, response.getBody());
    }

    @Test
    void updateOrderTest() {
        // Setup
        long orderId = 1L;
        Order updatedOrder = new Order();
        Long userId = 1L;
        List<Long> donerIds = List.of(1L, 2L);
        OrderDto expectedOrderDto = new OrderDto(orderId, List.of(), null);

        when(mOrderService.updateOrder(orderId, updatedOrder, userId, donerIds)).thenReturn(expectedOrderDto);

        // Act
        ResponseEntity<OrderDto> response = mOrderController.updateOrder(orderId, updatedOrder, userId, donerIds);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedOrderDto, response.getBody());
    }

    @Test
    void deleteOrderTest() {
        // Setup
        long orderId = 1L;

        // Act
        ResponseEntity<Void> response = mOrderController.deleteOrder(orderId);

        // Assert
        verify(mOrderService).deleteOrder(orderId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getAllOrdersTest() {
        // Setup
        List<OrderDto> expectedOrderDtos = List.of(new OrderDto(1L, List.of(), null),
                new OrderDto(2L, List.of(), null));

        when(mOrderService.getAllOrders()).thenReturn(expectedOrderDtos);

        // Act
        ResponseEntity<List<OrderDto>> response = mOrderController.getAllOrders();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedOrderDtos.size(), response.getBody().size());
        assertEquals(expectedOrderDtos, response.getBody());
    }

    @Test
    void correctConstructorTest() {
        OrderService orderServiceMock = mock(OrderServiceImpl.class);

        OrderController orderController = new OrderController(orderServiceMock);

        assertEquals(orderServiceMock, orderController.getMOrderService());
    }
}