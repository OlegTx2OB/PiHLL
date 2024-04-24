package com.example.papadoner.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

    @Test
    void idField_correctGenerationTypeStrategy_UserTest() throws NoSuchFieldException {
        var idField = User.class.getDeclaredField("id");
        var generatedValueAnnotation = idField.getAnnotation(GeneratedValue.class);
        assertNotNull(generatedValueAnnotation);
        assertEquals(GenerationType.IDENTITY, generatedValueAnnotation.strategy());
    }

    @Test
    void allArgsConstructor_CorrectConstructorData_UserTest() {
        // Setup
        long id = 1;
        long telephone = 1234567890;
        List<Order> orders = new ArrayList<>();

        // Act
        User user = new User(id, telephone, orders);

        // Assert
        assertEquals(id, user.getId());
        assertEquals(telephone, user.getTelephone());
        assertEquals(orders, user.getOrders());
    }

    @Test
    void gettersSetters_CorrectGettersSetters_UserTest() {
        // Setup
        User user = new User();

        long id = 1;
        long telephone = 1234567890;
        List<Order> orders = new ArrayList<>();

        // Act
        user.setId(id);
        user.setTelephone(telephone);
        user.setOrders(orders);

        // Assert
        assertEquals(id, user.getId());
        assertEquals(telephone, user.getTelephone());
        assertEquals(orders, user.getOrders());
    }
}