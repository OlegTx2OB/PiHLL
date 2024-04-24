package com.example.papadoner.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PriceByWeightDtoTest {

    @Test
    void allArgsConstructor_CorrectConstructorData_PriceByWeightDtoTest() {
        // Setup
        long id = 1;
        int weight = 500;
        double price = 0.5;

        // Act
        PriceByWeightDto priceByWeightDto = new PriceByWeightDto(id, weight, price);

        // Assert
        assertEquals(id, priceByWeightDto.getId());
        assertEquals(weight, priceByWeightDto.getWeight());
        assertEquals(price, priceByWeightDto.getPrice());
    }

    @Test
    void gettersSetters_CorrectGettersSetters_PriceByWeightDtoTest() {
        // Setup
        PriceByWeightDto priceByWeightDto = new PriceByWeightDto();

        long id = 1;
        int weight = 500;
        double price = 0.5;

        // Act
        priceByWeightDto.setId(id);
        priceByWeightDto.setWeight(weight);
        priceByWeightDto.setPrice(price);

        // Assert
        assertEquals(id, priceByWeightDto.getId());
        assertEquals(weight, priceByWeightDto.getWeight());
        assertEquals(price, priceByWeightDto.getPrice());
    }

    @Test
    void noArgsConstructor_DefaultValues_PriceByWeightDtoTest() {
        // Act
        PriceByWeightDto priceByWeightDto = new PriceByWeightDto();

        // Assert
        assertEquals(0, priceByWeightDto.getId());
        assertEquals(0, priceByWeightDto.getWeight());
        assertEquals(0.0, priceByWeightDto.getPrice());
    }
}