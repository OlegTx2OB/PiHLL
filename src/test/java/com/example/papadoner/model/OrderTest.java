package com.example.papadoner.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderTest {

    @Test
    void idField_correctGenerationTypeStrategy_OrderTest() throws NoSuchFieldException {
        var idField = Order.class.getDeclaredField("id");
        var generatedValueAnnotation = idField.getAnnotation(GeneratedValue.class);
        assertNotNull(generatedValueAnnotation);
        assertEquals(GenerationType.IDENTITY, generatedValueAnnotation.strategy());
    }

    @Test
    public void allArgsConstructor_CorrectConstructorData_OrderTest() {
        // Setup
        long id = 1;
        User user = new User();
        List<Doner> doners = new ArrayList<>();
        Date timestamp = new Date();

        // Act
        Order order = new Order(id, user, doners, timestamp);

        // Assert
        assertEquals(id, order.getId());
        assertEquals(user, order.getUser());
        assertEquals(doners, order.getDoners());
        assertEquals(timestamp, order.getTimestamp());
    }

    @Test
    public void gettersSetters_CorrectGettersSetters_OrderTest() {
        // Setup
        Order order = new Order();

        long id = 1;
        User user = new User();
        List<Doner> doners = new ArrayList<>();
        Date timestamp = new Date();

        // Act
        order.setId(id);
        order.setUser(user);
        order.setDoners(doners);
        order.setTimestamp(timestamp);

        // Assert
        assertEquals(id, order.getId());
        assertEquals(user, order.getUser());
        assertEquals(doners, order.getDoners());
        assertEquals(timestamp, order.getTimestamp());
    }
}
