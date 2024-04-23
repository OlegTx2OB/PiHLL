package com.example.papadoner.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PriceByWeightTest {

    @Test
    void idField_correctGenerationTypeStrategy_PriceByWeightTest() throws NoSuchFieldException {
        var idField = PriceByWeight.class.getDeclaredField("id");
        var generatedValueAnnotation = idField.getAnnotation(GeneratedValue.class);
        assertNotNull(generatedValueAnnotation);
        assertEquals(GenerationType.IDENTITY, generatedValueAnnotation.strategy());
    }

    @Test
    public void allArgsConstructor_CorrectConstructorData_PriceByWeightTest() {
        // Setup
        long id = 1;
        int weight = 500;
        double price = 0.5;

        // Act
        PriceByWeight priceByWeight = new PriceByWeight(id, weight, price);

        // Assert
        assertEquals(id, priceByWeight.getId());
        assertEquals(weight, priceByWeight.getWeight());
        assertEquals(price, priceByWeight.getPrice());
    }

    @Test
    public void gettersSetters_CorrectGettersSetters_PriceByWeightTest() {
        // Setup
        PriceByWeight priceByWeight = new PriceByWeight();

        long id = 1;
        int weight = 500;
        double price = 0.5;

        // Act
        priceByWeight.setId(id);
        priceByWeight.setWeight(weight);
        priceByWeight.setPrice(price);

        // Assert
        assertEquals(id, priceByWeight.getId());
        assertEquals(weight, priceByWeight.getWeight());
        assertEquals(price, priceByWeight.getPrice());
    }
}